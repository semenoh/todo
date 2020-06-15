package com.semenoh.todo.repository

import androidx.lifecycle.LiveData
import com.semenoh.todo.entity.Task
import com.semenoh.todo.source.local.TaskDao
import com.semenoh.todo.source.remote.TodoApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TasksRepository(
    private val dao: TaskDao,
    private val api: TodoApiService,
    private val dispatcher: CoroutineDispatcher
) {
    fun getAllItems(): LiveData<List<Task>> {
        return dao.getAllItems()
    }

    suspend fun addTask(task: Task) {
        withContext(dispatcher) {
            dao.add(task)
        }
    }

    suspend fun setCompleted(id: Long, isCompleted: Boolean) {
        withContext(dispatcher) {
            dao.setCompleted(id, isCompleted)
        }
    }

    suspend fun fetch() {
        withContext(dispatcher) {
            val allTasks = api.getAll()
            dao.add(
                *allTasks.toTypedArray()
            )
        }
    }
}