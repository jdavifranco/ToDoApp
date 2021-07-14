package com.jdavifranco.ufop.todolist.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.jdavifranco.ufop.todolist.R
import com.jdavifranco.ufop.todolist.database.TaskDatabase
import com.jdavifranco.ufop.todolist.databinding.ActivityCriarTaskBinding
import com.jdavifranco.ufop.todolist.model.*
import kotlinx.coroutines.CoroutineScope
import java.util.*

class CriarTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCriarTaskBinding
    private lateinit var viewModel:TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriarTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataSource = TaskDatabase.getInstance(applicationContext).taskDao
        val factory = TaskViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        binding.taskViewModel = viewModel
        insertListeners()
        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnNewTask.setOnClickListener {
            val task = Task(
                name = binding.tilTitle.text,
                date = binding.tilDate.text,
                hour = binding.tilHour.text
            )
            viewModel.insertTask(task)
            finish()
        }



    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

                binding.tilHour.text = "$hour:$minute"
            }

            timePicker.show(supportFragmentManager, null)
        }
    }

}