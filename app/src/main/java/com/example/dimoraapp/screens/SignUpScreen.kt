package com.example.dimoraapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalConfiguration
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

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val contact = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(Modifier.height(64.dp))
                Text(
                    text = stringResource(R.string.sup),
                    fontSize = 60.sp,
                    fontFamily = DMserif,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            val padding = if (isLandscape) 64.dp else 16.dp
            items(listOf(
                "Email" to email,
                "Username" to username,
                "Contact" to contact,
                "Password" to password,
                "Confirm Password" to confirmPassword
            )) { (label, state) ->
                TextField(

                    value = state.value,
                    onValueChange = { state.value = it },
                    label = { Text(label) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(start = padding, end = padding)
                        .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                        .align(alignment = Alignment.BottomEnd),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
            item {
                Row {
                    Text(text = "Already have an account? ", fontSize = 14.sp, color = Color.Gray)
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
                Spacer(Modifier.height(32.dp))
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("signin") },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp).size(80.dp),
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Navigate to sign in")
        }
    }
}
