package com.example.dimoraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import com.example.dimoraapp.navigation.AppNavigation
import com.example.dimoraapp.ui.theme.DimoraAppTheme

import com.example.dimoraapp.screens.InfoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DimoraAppTheme {
                AppNavigation()
            }
        }
    }
}
