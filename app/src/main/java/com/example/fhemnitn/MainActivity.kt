package com.example.fhemnitn


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fhemnitn.ui.AddStudentScreen
import com.example.fhemnitn.ui.StudentListScreen
import com.example.fhemnitn.ui.StudentViewModel
import com.example.fhemnitn.ui.theme.FhemniTNTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val vm: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FhemniTNTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "list") {
                    composable("list") {
                        StudentListScreen(vm) { nav.navigate("add") }
                    }
                    composable("add") {
                        AddStudentScreen(
                            onSave = { n, l -> vm.addStudent(n, l) },
                            onBack = { nav.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}