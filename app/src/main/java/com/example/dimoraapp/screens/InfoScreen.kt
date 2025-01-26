package com.example.dimoraapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.ui.theme.DMserif

@Composable
fun InfoScreen(navController: NavController){
    Text(
        text = "info screen",
        fontFamily = DMserif,
        fontSize = 35.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp)
    )
}