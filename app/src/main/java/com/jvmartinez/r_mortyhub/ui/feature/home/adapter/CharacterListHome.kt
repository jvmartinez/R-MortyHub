package com.jvmartinez.r_mortyhub.ui.feature.home.adapter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.jvmartinez.r_mortyhub.component.loading.LoadingComponent
import com.jvmartinez.r_mortyhub.core.data.model.Character

@Composable
fun CharacterListHome(
    items: List<Character>,
    loadMorePage: () -> Unit,
    itemContent: @Composable (item: Character) -> Unit,
    threshold: Int = 10,
    isLoading: Boolean = false
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleIndex = visibleItems.last().index
                    val totalItems = listState.layoutInfo.totalItemsCount
                    if (lastVisibleIndex >= totalItems - threshold && !isLoading) {
                        loadMorePage()
                    }
                }
            }
    }

    LazyColumn(state = listState) {
        items(items.size, key = { index -> items[index].id }) { index ->
            itemContent(items[index])
            if (index == items.size - 1 && isLoading) {
                LoadingComponent()
            }
        }
    }
}