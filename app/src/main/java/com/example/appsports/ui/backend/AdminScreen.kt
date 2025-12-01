package com.example.appsports.ui.backend

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AdminScreen(navController: NavHostController, nombre: String) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Administrador – $nombre") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

         //Tarjetitas de usuarios
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                onClick = {
                    navController.navigate("crudUsuarios")
                }
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Gestión de Usuarios", style = MaterialTheme.typography.headlineSmall)
                }
            }

            // Tarjetitas de equipos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Equipos (No funcional)")
                }
            }

            // Tarjetitas de partidos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Partidos (No funcional)")
                }
            }
        }
    }
}
