package com.example.fhemnitn.Data

import kotlinx.coroutines.flow.Flow

class StudentRepository(private val dao: StudentDao) {
    val students: Flow<List<Student>> = dao.getAll()

    suspend fun add(name: String, level: String) {
        dao.insert(Student(name = name, level = level))
    }
}