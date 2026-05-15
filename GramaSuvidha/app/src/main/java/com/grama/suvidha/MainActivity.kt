package com.grama.suvidha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.grama.suvidha.ui.theme.GramaSuvidhaTheme
import com.grama.suvidha.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GramaSuvidhaTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}