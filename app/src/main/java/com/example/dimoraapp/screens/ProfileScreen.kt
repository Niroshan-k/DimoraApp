package com.example.dimoraapp.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.R
import com.example.dimoraapp.navigation.BottomNavBar
import com.example.dimoraapp.ui.theme.DMserif

@Composable
fun ProfileScreen(navController: NavController){
    var isDrawerOpen by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopNavBar(onMenuClick = { isDrawerOpen = true })
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { content() }
                }
                BottomNavBar(navController = navController)
            }

            AnimatedVisibility(
                visible = isDrawerOpen,
                enter = slideInHorizontally(initialOffsetX = { -300 }),
                exit = slideOutHorizontally(targetOffsetX = { -300 })
            ) {
                SideNavBar(
                    onClose = { isDrawerOpen = false },
                    onAboutUsClick = { navController.navigate("about_us") }
                )
            }
        }
    }
}
@Composable
fun content() {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape)
    LazyRow(
        modifier = Modifier.padding(start = 100.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        item { Profilepicture() }
        item { ProfileDetails() }
    }
    else
        Column {
            Profilepicture()
            ProfileDetails()
        }
}

@Composable
fun Profilepicture () {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(R.drawable.profile),
            contentDescription = "profile"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetails() {
    val email = remember { mutableStateOf("example@gmail.com") }
    val username = remember { mutableStateOf("user123") }
    val contact = remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val padding = if (isLandscape) 64.dp else 16.dp
    val width = if (isLandscape) 600.dp else 500.dp
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = padding, end = padding)
    ) {
        // Define a list of pairs (label, state)
        val formFields = listOf(
            "Email" to email,
            "Username" to username,
            "Contact" to contact,
        )

        // Loop through the list and create a TextField for each pair
        formFields.forEach { (label, state) ->
            TextField(
                value = state.value,
                onValueChange = { state.value = it },
                label = { Text(label) },
                modifier = Modifier
                    .width(width)
                    .height(70.dp)
                    .padding(start = padding, end = padding, bottom = 16.dp)
                    .shadow(4.dp, shape = MaterialTheme.shapes.medium),
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            )
        }

        var password by rememberSaveable { mutableStateOf("12345678") }
        var visiblity by remember { mutableStateOf(false) }
        var icon = if (visiblity) painterResource(R.drawable.baseline_visibility_24)
        else painterResource(R.drawable.baseline_visibility_off_24)

        TextField(
            password, onValueChange = { password = it},
            placeholder = { Text("Password") },
            label = { Text("Password") },
            trailingIcon = {
                IconButton(onClick = { visiblity = !visiblity }) {
                    Icon(painter = icon
                    , contentDescription = "visible")
                }
            },
            visualTransformation = if (visiblity) VisualTransformation.None
            else PasswordVisualTransformation(),
            modifier = Modifier
                .width(width)
                .height(70.dp)
                .padding(start = padding, end = padding, bottom = 16.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box(
                modifier = Modifier.width(250.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = padding, end = padding)
                        .shadow(4.dp, shape = MaterialTheme.shapes.medium),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Update", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier.width(250.dp),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = padding, end = padding)
                        .shadow(4.dp, shape = MaterialTheme.shapes.medium),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Log Out", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}