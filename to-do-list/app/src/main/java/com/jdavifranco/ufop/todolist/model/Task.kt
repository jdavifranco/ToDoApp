package com.jdavifranco.ufop.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Data class são classes criadas com o único objetivo de guardar dados, nesse caso
os dados das tarefas.
A data classe task define apenas as propriedades da tarefa e um construtor
 */
@Entity(tableName = "tableTasks")
data class Task(
    @PrimaryKey
    val name:String,
    val date:String,
    val hour:String
)