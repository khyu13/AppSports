package com.example.appsports.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsports.ui.login.LoginScreen
import com.example.appsports.ui.backend.UsersCrudScreen
import com.example.appsports.ui.backend.AdminScreen


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Admin : Screen("adminScreen/{nombre}")
    object User : Screen("usuarioScreen/{nombre}")
}

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        // ADMIN
        composable(Screen.Admin.route) {
            val nombre = it.arguments?.getString("nombre") ?: ""
            AdminScreen(navController, nombre)
        }

        // USER
        composable("usuarioScreen/{nombre}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            UserScreen(navController, nombre)
        }

        composable("crudUsuarios") {
            UsersCrudScreen()
        }


    }
}



@Composable
fun UserScreen(navController: NavHostController, nombre: String){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Bienvenido/a Usuario!: $nombre")
    }
}


