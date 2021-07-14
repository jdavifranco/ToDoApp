package com.jdavifranco.ufop.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.ufop.todolist.R
import com.jdavifranco.ufop.todolist.database.TaskDatabase
import com.jdavifranco.ufop.todolist.databinding.ActivityMainBinding
import com.jdavifranco.ufop.todolist.model.TaskViewModel
import com.jdavifranco.ufop.todolist.model.TaskViewModelFactory
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterTasks: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataSource = TaskDatabase.getInstance(applicationContext).taskDao
        val factory = TaskViewModelFactory(dataSource)
        val taskViewModel =
            ViewModelProvider(
                this, factory).get(TaskViewModel::class.java)
        binding.taskViewModel = taskViewModel
        adapterTasks = TaskAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.rvTasks.layoutManager = layoutManager
        binding.rvTasks.adapter = adapterTasks
        binding.rvTasks.setHasFixedSize(true)

        taskViewModel.tasks.observe(this, Observer {
            it?.let {
                adapterTasks.submitList(it)
            }
        })


    }
}

