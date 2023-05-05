package com.example.lospibes.features.home.view_model.scaffold

sealed class ScaffoldEvent {
    object ShowBottomBar : ScaffoldEvent()
    object HideBottomBar : ScaffoldEvent()
}
