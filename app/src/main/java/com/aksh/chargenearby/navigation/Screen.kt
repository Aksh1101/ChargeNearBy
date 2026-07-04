package com.aksh.chargenearby.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val iconRes: ImageVector? = null
) {

    data object Signin : Screen("signin")
    data object Signup : Screen("signup")

    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object Map : Screen("map" , "Map", Icons.Default.LocationOn)
    data object Saved : Screen("saved", "Saved", Icons.Default.Bookmark)
    data object Profile : Screen("profile", "Profile", Icons.Default.Person)
    data object Landing1 : Screen("landing1")
    data object Landing2 : Screen("landing2")
    data object Splash : Screen("splash")
    data object Main : Screen("main")

}