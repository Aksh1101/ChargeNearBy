package com.aksh.chargenearby.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aksh.chargenearby.R
import com.aksh.chargenearby.navigation.Screen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center)
    {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Image(painter = painterResource(R.drawable.icon),
                contentDescription = "splashIcon",
                modifier = Modifier.size(144.dp))

            Text(text = stringResource(R.string.splash_intro),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp))
        }
    }
    LaunchedEffect(Unit) {
        delay(2000.milliseconds)

        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }


}