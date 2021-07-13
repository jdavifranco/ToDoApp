package com.jdavifranco.ufop.todolist.model
/*
Data class são classes criadas com o único objetivo de guardar dados, nesse caso
os dados das tarefas.
A data classe task define apenas as propriedades da tarefa e um construtor
 */
data class Task(
    val name:String,
    val date:String,
    val hour:String
)