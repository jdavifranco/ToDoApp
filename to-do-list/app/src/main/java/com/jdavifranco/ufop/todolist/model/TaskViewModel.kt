package com.jdavifranco.ufop.todolist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jdavifranco.ufop.todolist.database.TaskDao
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/*
Seguindo a arquitetura MVVM(Model View viewmodel)
será utilizo um viewmodel para acessar os dados das tasks
e fazer operações sobre esses dados
 */
class TaskViewModel(val database: TaskDao) : ViewModel() {


    fun getTasks() = database.getAllTasks()

     fun insertTask(task:Task){
        viewModelScope.launch {
            database.insert(task)
        }
    }

    fun deleteAll (){
        viewModelScope.launch {
            database.deleteAll()
        }
    }

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