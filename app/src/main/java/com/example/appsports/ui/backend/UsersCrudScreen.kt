package com.example.appsports.ui.backend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsports.data.LoginRepository
import com.example.appsports.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersCrudScreen() {

    var usuarios by remember {
        mutableStateOf(LoginRepository.obtenerUsuarios())
    }
    var showDialog by remember { mutableStateOf(false) }
    var editingUser by remember { mutableStateOf<User?>(null) }
    var userToDelete by remember { mutableStateOf<User?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Usuarios") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingUser = null
                    showDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir usuario")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(usuarios, key = { it.id }) { user ->

                Card(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            Text(user.nombre, fontSize = 20.sp)
                            Text(user.email, color = Color.Gray)
                            Text("Rol: ${user.rol}")
                        }

                        Row {
                            IconButton(onClick = {
                                editingUser = user
                                showDialog = true
                            }) {
                                Icon(Icons.Filled.Edit, contentDescription = "Editar")
                            }

                            IconButton(onClick = {
                                userToDelete = user
                            }) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        UserEditDialog(
            user = editingUser,
            onConfirm = { newUser ->

                usuarios = if (editingUser == null) {
                    usuarios + newUser
                } else {
                    usuarios.map { existing ->
                        if (existing.id == newUser.id) newUser else existing
                    }
                }

                showDialog = false
                editingUser = null
            },
            onDismiss = { showDialog = false }
        )
    }

    if (userToDelete != null) {
        AlertDialog(
            onDismissRequest = { userToDelete = null },
            title = { Text("Eliminar usuario") },
            text = { Text("¿Eliminar a ${userToDelete!!.nombre}?") },
            confirmButton = {
                TextButton(onClick = {
                    usuarios = usuarios.filter { it.id != userToDelete!!.id }
                    userToDelete = null
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { userToDelete = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Composable
fun UserEditDialog(
    user: User?,
    onConfirm: (User) -> Unit,
    onDismiss: () -> Unit
) {
    var nombre by remember { mutableStateOf(user?.nombre ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var rol by remember { mutableStateOf(user?.rol ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (user == null) "Añadir usuario" else "Editar usuario") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = rol, onValueChange = { rol = it }, label = { Text("Rol") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val newUser = User(
                    id = user?.id ?: (1000..999999).random(),
                    nombre = nombre,
                    email = email,
                    password = user?.password ?: "1234",
                    rol = rol
                )
                onConfirm(newUser)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

