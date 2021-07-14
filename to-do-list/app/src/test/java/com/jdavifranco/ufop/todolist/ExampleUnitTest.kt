package com.jdavifranco.ufop.todolist

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jdavifranco.ufop.todolist.database.TaskDao
import com.jdavifranco.ufop.todolist.database.TaskDatabase
import com.jdavifranco.ufop.todolist.model.Task
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException


/**
 * Teste para conferir se Database está funcionando
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    private lateinit var taskDao:TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        taskDao = db.taskDao
    }

    @Test
    @Throws(Exception::class)
    fun insertTask() {
        val task1 = Task("correr", "07/10", "14:00")
        val task2 = Task("pular", "07/10", "14:00")
        taskDao.insert(task1)
        taskDao.insert(task2)
        val res = taskDao.getTaskbyName("pular")
        assertEquals("pular", res.name )

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}