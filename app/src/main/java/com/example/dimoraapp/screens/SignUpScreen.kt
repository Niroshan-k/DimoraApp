package com.example.dimoraapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.ui.theme.DMserif
import com.example.dimoraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    // States for input fields and radio buttons
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val nic = remember { mutableStateOf("") }
    val selectedRole = remember { mutableStateOf("Customer") }

    // Big Sign Up Text
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = stringResource(R.string.sup),
            fontSize = 60.sp,
            fontFamily = DMserif
        )
    }
    Spacer(modifier = Modifier.height(50.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Email Input
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium, // Rounded corners
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEFEFE9)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Username Input
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEFEFE9)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // NIC Input
        TextField(
            value = nic.value,
            onValueChange = { nic.value = it },
            label = { Text("NIC") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEFEFE9)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEFEFE9)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Input
        TextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEFEFE9)
            )
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Radio Buttons for Role Selection
        Text(text = "Select Role", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = selectedRole.value == "Customer",
                onClick = { selectedRole.value = "Customer" }
            )
            Text(text = "Customer", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedRole.value == "Seller",
                onClick = { selectedRole.value = "Seller" }
            )
            Text(text = "Seller", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Already Have an Account Section
        Row {
            Text(
                text = "Already have an account? ",
                fontSize = 14.sp,
                color = Color.Gray
            )
            ClickableText(
                text = AnnotatedString("Sign in"),
                onClick = { navController.navigate("signin") },
                style = LocalTextStyle.current.copy(
                    color = Color.Blue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    // Floating Action Button for Navigation to Sign In Screen
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = { navController.navigate("signin") },
            containerColor = Color(0xFF28302B),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd) // Fix: Correct use of alignment
                .padding(16.dp)
                .size(60.dp),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Navigate to sign in"
            )
        }
    }
}
