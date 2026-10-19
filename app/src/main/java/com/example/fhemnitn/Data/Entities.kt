package com.example.fhemnitn.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val level: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)

@Entity
data class ErrorType(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subjectId: Long,
    val label: String
)

@Entity
data class StudySession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val studentId: Long,
    val date: Long = System.currentTimeMillis(),
    val note: String = ""
)

@Entity
data class MistakeLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: Long,
    val errorTypeId: Long,
    val comment: String = ""
)