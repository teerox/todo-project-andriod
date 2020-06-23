package com.example.todo_application.repository

import androidx.lifecycle.LiveData
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems

interface TodoRepository<T> {
    fun getTodoAndTodoList(): LiveData<List<TodoAndTodoList>>

    fun getAllTodo():LiveData<List<Todo>>


    fun getAllTodoItem():LiveData<List<TodoItems>>

    fun getSingleAll(id: Int):LiveData<List<TodoAndTodoList>>

    fun getSingleTodo(id: Int):LiveData<List<Todo>>

    fun getSingleTodoItem(Id: Int):LiveData<List<TodoItems>>

    suspend fun insertAllTodo(todo: Todo)

    suspend fun insertAllTodoItems(todo: TodoItems)

    suspend fun deleteTodoById(todoId: Int): Int

    suspend fun updateTodo(todo: TodoItems)
}