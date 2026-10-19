package com.example.fhemnitn.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fhemnitn.Data.AppDatabase
import com.example.fhemnitn.Data.Student
import com.example.fhemnitn.Data.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel (app: Application) : AndroidViewModel(app) {
    private val repo = StudentRepository(AppDatabase.get(app).studentDao())

    val students: StateFlow<List<Student>> = repo.students
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addStudent(name: String, level: String) {
        viewModelScope.launch { repo.add(name, level) }
    }
}