package com.example.todo_application.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems


@Dao
interface TodoDAO {
//    @Query("SELECT * FROM todo ORDER BY  DESC")
//    fun getAll():LiveData<List<Todo>>

    @Transaction
    @Query("SELECT * FROM Todo")
    fun getTodoAndTodoList(): LiveData<List<TodoAndTodoList>>

    @Query("SELECT * FROM Todo ORDER BY todoId DESC")
    fun getAllTodo():LiveData<List<Todo>>


    @Query("SELECT * FROM TodoItems ORDER BY todoListId DESC")
    fun getAllTodoItem():LiveData<List<TodoItems>>


    @Query("SELECT * FROM Todo WHERE todoId = :id")
    fun getSingleAll(id: Int):LiveData<List<TodoAndTodoList>>

    @Query("SELECT * FROM Todo WHERE todoId = :id")
    fun getSingleTodo(id: Int):LiveData<List<Todo>>

    @Query("SELECT * FROM TodoItems WHERE listItemId = :Id")
    fun getSingleTodoItem(Id: Int):LiveData<List<TodoItems>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTodo(vararg todos: Todo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTodoItems(vararg todo: TodoItems)


    @Transaction
    @Query("DELETE FROM Todo WHERE todoId = :todoId")
    fun deleteTodoById(todoId: Int): Int

    @Update
    fun updateTodo(todo: TodoItems)

}