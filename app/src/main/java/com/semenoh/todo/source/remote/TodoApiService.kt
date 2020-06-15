package com.semenoh.todo.source.remote

import com.semenoh.todo.entity.Task
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos?userId=1")
    suspend fun getAll(): List<Task>
}