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
import com.aksh.chargenearby.R

@Preview
@Composable
fun LandingPage2() {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
            Image(painter = painterResource(id = R.drawable.img3),
                contentDescription = "img3",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )

        Text(text = "Seamless Navigation \n    to your Charger",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(40.dp))
    }

}