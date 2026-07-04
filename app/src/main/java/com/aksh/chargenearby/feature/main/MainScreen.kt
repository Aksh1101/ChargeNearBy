package com.aksh.chargenearby.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aksh.chargenearby.core.model.bottomBarItems
import com.aksh.chargenearby.navigation.MainNavHost
import com.aksh.chargenearby.navigation.Screen


@Composable
fun MainScreen(){

    val navController = rememberNavController()

    Scaffold(bottomBar ={MainBottomBar(navController)} ) {
            innerPadding ->

        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

}


}



@Composable
fun MainBottomBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(){
        bottomBarItems.forEach { screen ->
            NavigationBarItem(
                icon = {
                    // Safely unpack the optional ImageVector icon
                    screen.iconRes?.let { imageVector ->
                        Icon(
                            imageVector = imageVector,
                            contentDescription = screen.title
                        )
                    }
                },
                label = {
                    // Use fallback empty text if title is null
                    Text(text = screen.title ?: "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route){

                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true

                    }
                }
            )
        }

    }


}