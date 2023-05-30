package com.example.lospibes.features.home.presentation.explore.presentation

import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto

sealed class ExploreEvent {
    data class EnteredActive(val value: Boolean) : ExploreEvent()
    data class EnteredQueryName(val value: String) : ExploreEvent()
    data class EnteredQuery(val value: SearchProductsQueryDto) : ExploreEvent()
    object OnOpenBottomSheet : ExploreEvent()
    object OnHideBottomSheet : ExploreEvent()
    object OnSearchBarOpen : ExploreEvent()
    object OnSearchBarClose : ExploreEvent()
}
