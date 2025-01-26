package com.example.dimoraapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dimoraapp.screens.GetStartedScreen
import com.example.dimoraapp.screens.SignInScreen
import com.example.dimoraapp.screens.SignUpScreen
import com.example.dimoraapp.screens.HomeScreen
import com.example.dimoraapp.screens.InfoScreen
import com.example.dimoraapp.screens.ProfileScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "getStarted"
    ) {
        composable("getStarted") { GetStartedScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("signin") { SignInScreen(navController) }
        composable("homescreen") { HomeScreen(navController) }
        composable("infoscreen"){ InfoScreen(navController) }
        composable("profilescreen"){ ProfileScreen(navController) }
    }
}
