package com.jvmartinez.r_mortyhub.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jvmartinez.r_mortyhub.navigation.route.NavigateRoute
import com.jvmartinez.r_mortyhub.ui.feature.home.HomeScreen
import com.jvmartinez.r_mortyhub.ui.feature.home.HomeViewModel
import com.jvmartinez.r_mortyhub.ui.feature.splash.SplashScreen


@Composable
fun NavigationApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigateRoute.SplashScreen.route
    ) {
        composable(NavigateRoute.SplashScreen.route) {
            val navigateToHome = {
                navController.navigate(NavigateRoute.HomeScreen.route)
            }
            SplashScreen(
                viewModel = hiltViewModel(),
                navigateToHome
            )
        }
        composable(NavigateRoute.HomeScreen.route) {
            HomeScreen(
                viewModel = hiltViewModel<HomeViewModel>()
            )
        }
    }
}