package com.example.fhemnitn.Data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class, Subject::class, ErrorType::class,
        StudySession::class, MistakeLog::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun mistakeDao(): MistakeDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fhemni.db"
                ).build().also { INSTANCE = it }
            }
    }
}