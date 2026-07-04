package com.aksh.chargenearby.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aksh.chargenearby.R

@Composable
fun LandingPage1(navController: NavController){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Image(painter = painterResource(id = R.drawable.img1),
            contentDescription = "img1",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )

        Text(text = "Find the Charger \n         Easily",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            modifier = Modifier.padding(50.dp))
    }

}