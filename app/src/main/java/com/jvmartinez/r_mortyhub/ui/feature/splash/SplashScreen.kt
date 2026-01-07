package com.jvmartinez.r_mortyhub.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jvmartinez.r_mortyhub.R
import com.jvmartinez.r_mortyhub.component.loading.LoadingComponent
import com.jvmartinez.r_mortyhub.ui.theme.VibrantCyan

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToHome: () -> Unit
) {
    val loading by viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(loading) {
        if (!loading) {
            navigateToHome()
        }
    }
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VibrantCyan)
                .padding(paddingValues)
        ) {
            SplashScreenContent()
        }
    }
}

@Composable
private fun SplashScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rick_and_morty),
            contentDescription = "Logo"
        )
        Spacer(Modifier.height(20.dp))
        LoadingComponent()
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(hiltViewModel(),{})
}