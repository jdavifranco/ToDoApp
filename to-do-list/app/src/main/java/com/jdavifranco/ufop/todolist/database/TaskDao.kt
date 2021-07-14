package com.jdavifranco.ufop.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jdavifranco.ufop.todolist.model.Task

/*
Nesse projeto, como melhora no original será adicionado um banco de dados local
utilizando a biblioteca jetpack Room.
 */
/*
A Dao siginfica Data Access object, é uma interdace contendo os
métodos de acesso ao banco de dados, queries e etc.
Essa terá apenas um crud básico
 */
/*É Necessário usar as anotações da biblioteca Room para que ela
indetifique a Dao
 */
@Dao
interface TaskDao {
    /*
    Com a anotação @Insert posso definir que essa função que adiciona uma task ao banco de dados
     */
    @Insert
    fun insert(task: Task)
    /*
    A anotação @Query serve para definir uma consulta mais específica ao banco de dados
    Utilizando linguagem SQL
     */
    @Query("SELECT * FROM task_table")
    fun getAllTasks():LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE name = :Id")
    fun getTaskbyName(Id:String):Task

    @Query("DELETE FROM task_table")
    fun deleteAll()
}