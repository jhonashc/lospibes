package com.example.lospibes.features.home.viewmodel.scaffold

sealed class ScaffoldEvent {
    object ShowBottomBar : ScaffoldEvent()
    object HideBottomBar : ScaffoldEvent()
}
