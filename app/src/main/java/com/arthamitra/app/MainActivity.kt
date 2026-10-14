package com.arthamitra.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arthamitra.app.ui.screens.HomeScreen
import com.arthamitra.app.ui.screens.InvestScreen
import com.arthamitra.app.ui.screens.LoanScreen
import com.arthamitra.app.ui.screens.ServicesScreen
import com.arthamitra.app.ui.theme.ArthaMithraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArthaMithraTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("loans") { LoanScreen(navController) }
                        composable("invest") { InvestScreen(navController) }
                        composable("services") { ServicesScreen(navController) }
                    }
                }
            }
        }
    }
}