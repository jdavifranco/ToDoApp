package com.jdavifranco.ufop.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.ufop.todolist.databinding.ItemTaskBinding
import com.jdavifranco.ufop.todolist.model.Task

class TaskAdapter: ListAdapter<Task, TaskAdapter.TaskViewHolder>(AdapterDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item)
    }

    class TaskViewHolder private constructor(val binding:ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind (item:Task){
            binding.task = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                var layoutInflater = LayoutInflater.from(parent.context)
                var binding = ItemTaskBinding.inflate(layoutInflater, parent, false)
                return TaskViewHolder(binding)
            }
        }
    }
    class AdapterDiffCallBack : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
