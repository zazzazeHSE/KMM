package com.example.transportschedule.android.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.toLowerCase
import com.example.transportschedule.android.commonViews.Loader
import com.example.transportschedule.android.commonViews.errorView


@Composable
fun SearchScreen(
    showRoutes: (String, String) -> Unit,
    model: SearchViewModel = viewModel()
) {
    val state by model.stateLiveData.observeAsState(SearchState(listOf(), true, null, null, null))
    when {
        state.isLoading -> {
            loadingView()
        }
        state.error != null -> {
            errorView(errorText = state.error!!)
        }
        else -> {
            Column(
                Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .fillMaxSize()
            ) {
                SearchInput(title = "From", suggestions = state.availableStations) {
                    model.setFromStation(it)
                }
                SearchInput(title = "To", suggestions = state.availableStations) {
                    model.setToStation(it)
                }
                Button(onClick = {
                    val fromCode = model.stateLiveData.value?.fromStation?.code
                    val toCode = model.stateLiveData.value?.toStation?.code
                    if (fromCode != null && toCode != null) {
                        showRoutes(fromCode, toCode)
                    }
                },
                    enabled = model.searchEnabled) {
                    Text(text = "Search")
                }
            }
        }
    }
}

@Composable
fun loadingView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Loader(text = "Загружаем аэропорты...")
    }
}