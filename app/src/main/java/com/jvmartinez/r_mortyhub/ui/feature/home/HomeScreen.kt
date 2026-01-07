package com.jvmartinez.r_mortyhub.ui.feature.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jvmartinez.r_mortyhub.R
import com.jvmartinez.r_mortyhub.component.button.ButtonComponent
import com.jvmartinez.r_mortyhub.component.loading.LoadingComponent
import com.jvmartinez.r_mortyhub.ui.base.StatusData
import com.jvmartinez.r_mortyhub.ui.feature.home.adapter.CharacterItem
import com.jvmartinez.r_mortyhub.ui.feature.home.adapter.CharacterListHome
import com.jvmartinez.r_mortyhub.ui.feature.home.data.HomeUiState
import com.jvmartinez.r_mortyhub.ui.theme.VibrantCyan

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.homeState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(VibrantCyan),
    ) { paddingValues ->
        Body(paddingValues, viewModel, state)
    }
}

@Composable
private fun Body(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    state: StatusData<HomeUiState>
) {
    var pageCurrency by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (state) {
            is StatusData.Error -> {
                NotificationError {
                    viewModel.getCharacters()
                }
            }

            StatusData.Empty, StatusData.Loading -> {
                if (pageCurrency == 1) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingComponent()
                    }
                }
            }

            is StatusData.Success -> {
                val data = state.data
                CharacterListHome(
                    items = data.characters,
                    loadMorePage = {
                        pageCurrency += 1
                        viewModel.getCharacters(pageCurrency)
                    },
                    itemContent = { item ->
                        CharacterItem(item.image, item.name, item.status)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                )
            }
        }
    }
}

@Composable
fun NotificationError(
    action: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_morty_smith),
            contentDescription = "notification de error"
        )
        Spacer(modifier = Modifier.height(20.dp))
        ButtonComponent(
            titleButton = "Reintentar",
            isEnabled = true,
            action = { action() }
        )
    }
}