package com.semenoh.todo.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenoh.todo.entity.Task
import com.semenoh.todo.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ListViewModel : ViewModel(), KoinComponent {
    private val repository: TasksRepository by inject()

    val items: LiveData<List<ListItemTodo>> = Transformations.map(repository.getAllItems()) {
        return@map it.reversed()
            .map { entity ->
                ListItemTodo(
                    entry = entity,
                    onChecked = { toggleCheckForeItem(it) }
                )
            }
    }

    private fun toggleCheckForeItem(item: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCompleted(item.id, item.completed.not())
        }
    }


    fun addItem(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch {
                repository.addTask(Task(title))
            }
        }
    }

    fun fetch() {
        viewModelScope.launch() {
            repository.fetch()
        }
    }
}