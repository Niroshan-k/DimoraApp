package com.example.dimoraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dimoraapp.navigation.AppNavigation
import com.example.dimoraapp.ui.theme.DimoraAppTheme

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
