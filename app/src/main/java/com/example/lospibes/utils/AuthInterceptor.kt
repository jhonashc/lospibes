package com.example.lospibes.utils

import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.use_case.preference.auth.AuthManagerUseCase
import com.example.lospibes.features.auth.data.dto.body.CreateRefreshTokenDto
import com.example.lospibes.features.auth.data.source.remote.AuthService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptor @Inject constructor(
    private val authService: Provider<AuthService>,
    private val authManagerUseCase: AuthManagerUseCase,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val auth: Auth = runBlocking {
            authManagerUseCase.getAuthManager().first()
        }

        val response = chain.proceed(
            newRequestWithAccessToken(
                accessToken = auth.accessToken,
                request = request
            )
        )

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val refreshResponse = runBlocking {
                authService.get().refreshToken(
                    CreateRefreshTokenDto(
                        refreshToken = auth.refreshToken
                    )
                )
            }

            if (refreshResponse.isSuccessful) {
                val newAccessToken = refreshResponse.body()?.accessToken

                if (newAccessToken != null) {
                    response.close()

                    runBlocking {
                        authManagerUseCase.setAuthManager(
                            Auth(
                                accessToken = newAccessToken,
                                refreshToken = auth.refreshToken,
                                userId = auth.userId
                            )
                        )
                    }

                    return chain.proceed(
                        newRequestWithAccessToken(
                            accessToken = newAccessToken,
                            request = request
                        )
                    )
                }
            }
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}