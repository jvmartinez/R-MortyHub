package com.jvmartinez.r_mortyhub.navigation.route

sealed class NavigateRoute(val route: String) {
    object SplashScreen : NavigateRoute("splash_screen")
    object HomeScreen : NavigateRoute("home_screen")
}