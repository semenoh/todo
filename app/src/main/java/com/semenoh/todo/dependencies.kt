package com.semenoh.todo

import androidx.room.Room
import com.semenoh.todo.repository.TasksRepository
import com.semenoh.todo.source.local.AppDatabase
import com.semenoh.todo.source.local.TaskDao
import com.semenoh.todo.source.remote.TodoApiService
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dependencies = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "todo"
        ).build()
    }

    single<TaskDao> {
        get<AppDatabase>()
            .todoDao()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<TodoApiService> {
        get<Retrofit>()
            .create(TodoApiService::class.java)
    }

    single<TasksRepository> {
        TasksRepository(
            get<TaskDao>(),
            get<TodoApiService>(),
            Dispatchers.IO
        )
    }
}