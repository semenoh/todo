package com.semenoh.todo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val completed: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}