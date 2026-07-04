package com.aksh.chargenearby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aksh.chargenearby.feature.bookmark.BookmarkScreen
import com.aksh.chargenearby.feature.home.HomeScreen
import com.aksh.chargenearby.feature.main.MainBottomBar
import com.aksh.chargenearby.feature.main.MainScreen
import com.aksh.chargenearby.feature.map.MapScreen
import com.aksh.chargenearby.feature.profile.ProfileScreen
import com.aksh.chargenearby.ui.screens.LandingPage1
import com.aksh.chargenearby.ui.screens.LandingPage2
import com.aksh.chargenearby.ui.screens.SplashScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable("splash") {
            SplashScreen(navController)
        }
        composable("landing1") {
            LandingPage1(navController)
        }
        composable("landing2") {
            LandingPage2(navController)
        }

        composable(Screen.Main.route) {
            MainScreen()
        }


    }

}
