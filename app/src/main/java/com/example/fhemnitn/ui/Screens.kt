package com.example.fhemnitn.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    vm: StudentViewModel,
    onAddClick: () -> Unit
) {
    val students by vm.students.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Fhemni - Tlamidh") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }
    ) { padding ->
        if (students.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding).padding(24.dp)) {
                Text("Ma 3andek 7ata tilmidh. Dhghat 3la + bach tzid.")
            }
        } else {
            LazyColumn(Modifier.padding(padding)) {
                items(students) { s ->
                    ListItem(
                        headlineContent = { Text(s.name) },
                        supportingContent = { Text(s.level) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(
    onSave: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Tilmidh jdid") }) }
    ) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Esm") }, modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = level, onValueChange = { level = it },
                label = { Text("Niveau (ex: 3eme)") }, modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(name.trim(), level.trim())
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Sauvegarder") }
        }
    }
}