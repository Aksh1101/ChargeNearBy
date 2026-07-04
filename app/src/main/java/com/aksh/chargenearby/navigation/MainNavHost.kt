package com.aksh.chargenearby.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aksh.chargenearby.feature.bookmark.BookmarkScreen
import com.aksh.chargenearby.feature.home.HomeScreen
import com.aksh.chargenearby.feature.map.MapScreen
import com.aksh.chargenearby.feature.profile.ProfileScreen

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier) {


    NavHost(navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier){

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Map.route) {
            MapScreen(navController)
        }
        composable(Screen.Saved.route) {
            BookmarkScreen(navController)
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }
    }

}