package com.aksh.chargenearby.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SignInScreen() {

    val scrollState = rememberScrollState()
    var name by rememberSaveable{ mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isChecked by rememberSaveable { mutableStateOf(false)}

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(100.dp))

            Text(text = "Sign In",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 40.sp,
                color = Color.Black
            )

            Text(text = "")

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = {Text(text = "Email",
                    fontWeight = FontWeight.SemiBold)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription = "mail")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

             OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = {Text(text = "Password")},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock,
                        contentDescription = "password")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {},
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF22C55E)
                )
            ) {
                Text(text = "Forgot Password")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF22C55E)
                )) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Or Sign up with")

            // add google sign in
        }
    }

}