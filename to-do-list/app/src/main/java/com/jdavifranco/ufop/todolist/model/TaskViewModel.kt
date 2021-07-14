package com.jdavifranco.ufop.todolist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jdavifranco.ufop.todolist.database.TaskDao

/*
Seguindo a arquitetura MVVM(Model View viewmodel)
será utilizo um viewmodel para acessar os dados das tasks
e fazer operações sobre esses dados
 */
class TaskViewModel(val database: TaskDao) : ViewModel() {
    val tasks = database.getAllTasks()
}

class TaskViewModelFactory(
    private val dataSource: TaskDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}