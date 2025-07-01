package com.example.learningapplication.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learningapplication.routes.Routes

@Composable
fun LoginUi(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Jetpack Compose",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                value = userName,
                onValueChange = { userName = it },
                labelText = "Name",
                hintText = "Enter your name",
                icon = Icons.Default.Person
            )
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                labelText = "Email",
                hintText = "Enter your email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
            )
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                labelText = "Password",
                hintText = "Enter your password",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(
                    mask = '*'
                )
            )

            Button(
                onClick = {
                    if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    } else {
                        navController.navigate(Routes.homeRoute(userName))
                        Toast.makeText(context, "Welcome $userName", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    hintText: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = onValueChange,
        label = {
            Text(text = labelText)
        },
        placeholder = {
            Text(text = hintText)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = labelText
            )
        },
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth()
    )
}