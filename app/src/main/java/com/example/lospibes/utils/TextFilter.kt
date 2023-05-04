package com.example.lospibes.utils

import java.text.DecimalFormat

private val decimalFormatter = DecimalFormat("#,##0.00")

fun capitalizeText(text: String): String {
    return text.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun formatNumber(number: Number): String {
    return decimalFormatter.format(number)
}