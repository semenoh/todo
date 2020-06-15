package com.semenoh.todo.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semenoh.todo.entity.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TaskDao
}