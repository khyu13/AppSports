package com.example.appsports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appsports.navigation.NavigationRoot
import com.example.appsports.ui.theme.AppSportsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSportsTheme {
                NavigationRoot()
            }
        }
    }
}




