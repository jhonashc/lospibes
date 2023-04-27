package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.R
import com.example.lospibes.core.component.*
import com.example.lospibes.features.home.component.ComboListGrid
import com.example.lospibes.features.home.component.ProductListGrid
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.TabItem

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToFilter: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val exploreState = exploreViewModel.state.collectAsState()
    val queryState = exploreViewModel.queryState.collectAsState()

    LaunchedEffect(key1 = queryState.value) {
        exploreViewModel.getCategories()
        exploreViewModel.getProducts(
            getProductsQueryDto = queryState.value.getProductsQueryDto
        )
    }

    StandardColumnContainer(
        isLoading = exploreState.value.isCategoryLoading &&
                exploreState.value.isComboLoading &&
                exploreState.value.isProductLoading,
        message = exploreState.value.message
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                onNavigateToHome = onNavigateToHome,
                onNavigateToFilter = onNavigateToFilter
            )

            Body(
                exploreViewModel = exploreViewModel,
                exploreState = exploreState,
                queryState = queryState,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit,
    onNavigateToFilter: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val onValueChange = { newValue: String -> value = newValue }

    var isVisibleSearchBar by remember { mutableStateOf(false) }

    if (!isVisibleSearchBar) {
        StandardTopBar(
            title = "Explorar",
            navigationIcon = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier.padding(end = 5.dp),
                    onClick = { isVisibleSearchBar = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon"
                    )
                }
            },
            onBackTo = onNavigateToHome
        )
    } else {
        SearchTopBar(
            value = value,
            filterIcon = {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.outline
                    ),
                    onClick = {
                        onNavigateToFilter()
                        isVisibleSearchBar = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_filter_list_24),
                        contentDescription = "Filter Icon"
                    )
                }
            },
            onSubmit = {},
            onClose = {
                value = ""
                isVisibleSearchBar = false
            },
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun Body(
    exploreViewModel: ExploreViewModel,
    exploreState: State<ExploreState>,
    queryState: State<QueryState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val categoryList: List<Category> = exploreState.value.categoryList

    if (categoryList.isNotEmpty()) {
        CategorySection(
            exploreViewModel = exploreViewModel,
            queryState = queryState,
            categoryList = categoryList
        )
    }

    ResultSection(
        exploreState = exploreState,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
private fun CategorySection(
    exploreViewModel: ExploreViewModel,
    queryState: State<QueryState>,
    categoryList: List<Category>
) {
    val tabList: List<TabItem> = categoryList.map { category ->
        TabItem(
            name = category.name
        )
    }

    val categoryQuery: String = queryState.value.getProductsQueryDto.category.orEmpty()

    val currentTab: TabItem = if (categoryQuery.isNotEmpty())
        TabItem(name = categoryQuery) else
        tabList[0]

    var selectedTab by remember { mutableStateOf(currentTab) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        StandardTabList(
            tabList = tabList,
            selectedTab = selectedTab,
            onTabSelected = { tabItem ->
                exploreViewModel.onEvent(
                    ExploreEvent.SelectedCategory(
                        value = tabItem.name
                    )
                )
                selectedTab = tabItem
            }
        )
    }
}

@Composable
fun ResultSection(
    exploreState: State<ExploreState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val comboList: List<Combo> = exploreState.value.comboList
    val productList: List<Product> = exploreState.value.productList

    if (comboList.isNotEmpty()) {
        ComboListGrid(
            combos = comboList,
            onComboSelected = { selectedCombo ->
                onNavigateToDetails(true, selectedCombo.id)
            }
        )
    } else {
        ProductListGrid(
            products = productList,
            onProductSelected = { selectedProduct ->
                onNavigateToDetails(false, selectedProduct.id)
            }
        )
    }
}