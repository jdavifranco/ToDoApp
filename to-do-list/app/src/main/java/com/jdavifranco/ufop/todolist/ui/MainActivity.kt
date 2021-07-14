package com.jdavifranco.ufop.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jdavifranco.ufop.todolist.database.TaskDatabase
import com.jdavifranco.ufop.todolist.databinding.ActivityMainBinding
import com.jdavifranco.ufop.todolist.model.Task
import com.jdavifranco.ufop.todolist.model.TaskViewModel
import com.jdavifranco.ufop.todolist.model.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterTasks: TaskAdapter
    private lateinit var taskViewModel:TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // datasource guarda uma referencia para o banco de dados
        val dataSource = TaskDatabase.getInstance(applicationContext).taskDao
        // ViewModelFactory que permite passar TaskDao como paramentro para o viewModel
        val factory = TaskViewModelFactory(dataSource)
        taskViewModel =
            ViewModelProvider(
                this, factory).get(TaskViewModel::class.java)
        binding.taskViewModel = taskViewModel
        adapterTasks = TaskAdapter()
        binding.rvTasks.adapter = adapterTasks
        binding.lifecycleOwner = this
        /*
        Utilizando LiveData é possível manter a lista sempre atualizada com
        os valores mais recentes.
         */
        /*
        Adicono um observer para a função getTasks que retorna um objeto
         */
        taskViewModel.tasks.observe(this, Observer {
                it.let {
                    if (it.size>0){
                        binding.includeEmpty.visibility = View.GONE
                        binding.rvTasks.visibility = View.VISIBLE

                    }
                    else{
                        binding.rvTasks.visibility = View.GONE
                        binding.includeEmpty.visibility = View.VISIBLE
                    }
                    adapterTasks.submitList(it)
                }
        })

        binding.fab.setOnClickListener {
                val intent = Intent(this, CriarTaskActivity::class.java)
                startActivity(intent)


        }
        binding.fabDelete.setOnClickListener {
            taskViewModel.deleteAll()
        }
    }

}

