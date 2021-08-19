package br.com.luanadev.ceepapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.luanadev.ceepapplication.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao
}