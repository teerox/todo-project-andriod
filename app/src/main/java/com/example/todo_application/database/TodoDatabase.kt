package com.example.todo_application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoItems


@Database(entities = [Todo::class,TodoItems::class],version = 6, exportSchema = false)
abstract class TodoDatabase:RoomDatabase(){
    abstract fun todoDao(): TodoDAO

}