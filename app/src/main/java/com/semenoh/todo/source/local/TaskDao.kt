package com.semenoh.todo.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.semenoh.todo.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllItems(): LiveData<List<Task>>

    @Query("UPDATE Task SET completed = :completed WHERE id == :id")
    suspend fun setCompleted(id: Long, completed: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg entry: Task)
}