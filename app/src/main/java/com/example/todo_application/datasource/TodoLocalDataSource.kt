package com.example.todo_application.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todo_application.database.TodoDAO
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems
import javax.inject.Inject

class TodoLocalDataSource @Inject constructor (private val todoDAO: TodoDAO):
    TodoDataSource<Todo> {
    override fun getTodoAndTodoList(): LiveData<List<TodoAndTodoList>> {
        return todoDAO.getTodoAndTodoList()
    }

    override fun getAllTodo(): LiveData<List<Todo>> {
        return todoDAO.getAllTodo()
    }

    override fun getAllTodoItem(): LiveData<List<TodoItems>> {
        return todoDAO.getAllTodoItem()
    }

    override fun getSingleAll(id: Int): LiveData<List<TodoAndTodoList>> {
       return todoDAO.getSingleAll(id)
    }

    override fun getSingleTodo(id: Int): LiveData<List<Todo>> {
        return todoDAO.getSingleTodo(id)
    }

    override fun getSingleTodoItem(Id: Int): LiveData<List<TodoItems>> {
       return todoDAO.getSingleTodoItem(Id)
    }

    override suspend fun insertAllTodo(todo: Todo) {
        todoDAO.insertAllTodo(todo)
    }

    override suspend fun insertAllTodoItems(todo: TodoItems) {
        todoDAO.insertAllTodoItems(todo)
    }

    override suspend fun deleteTodoById(todoId: Int): Int {
       return todoDAO.deleteTodoById(todoId)
    }

    override suspend fun updateTodo(item: TodoItems) {
       todoDAO.updateTodo(item)
    }


//    override fun getAllTodo(): LiveData<List<Todo>>? {
//        return todoDAO.getAll()
//    }
//
//    override fun getSingleTodo(item: Int): LiveData<List<Todo>>? {
//        return todoDAO.getSingleTodo(item)
//    }
//
//
//    override suspend fun saveTodo(item: Todo) {
//        todoDAO.insertAll(item)
//    }
//
//    override suspend fun deleteTodo(item: Int) {
//        todoDAO.deleteTodoById(item)
//    }
//
//    override suspend fun updateTodo(item: Todo) {
//        Log.e("update",item.toString())
//        Log.e("updateID",item.id.toString())
//        todoDAO.updateTodo(item)
//    }




}