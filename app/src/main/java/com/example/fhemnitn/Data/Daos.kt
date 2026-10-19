package com.example.fhemnitn.Data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class TopMistake(
    val errorTypeId: Long,
    val label: String,
    val times: Int
)

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student): Long

    @Query("SELECT * FROM Student ORDER BY name")
    fun getAll(): Flow<List<Student>>
}

@Dao
interface MistakeDao {
    @Insert
    suspend fun insertSession(session: StudySession): Long

    @Insert
    suspend fun insertMistake(mistake: MistakeLog)

    @Query("""
        SELECT m.errorTypeId AS errorTypeId, e.label AS label, COUNT(*) AS times
        FROM MistakeLog m
        JOIN StudySession s ON m.sessionId = s.id
        JOIN ErrorType e ON m.errorTypeId = e.id
        WHERE s.studentId = :studentId
        GROUP BY m.errorTypeId
        ORDER BY times DESC
        LIMIT 5
    """)
    fun topMistakes(studentId: Long): Flow<List<TopMistake>>
}