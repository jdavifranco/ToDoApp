package com.jdavifranco.ufop.todolist.model

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

private val locale = Locale("pt", "BR")

fun Date.format() : String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}
@BindingAdapter("setDataHora")
fun TextView.setDataHora(task:Task){
    val texto =  "Dia: ${task.date} Hora: ${task.hour}"
    text = texto
}

var TextInputLayout.text : String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }