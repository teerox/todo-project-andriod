package com.example.todo_application.screens.todoviewmodel

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.LiveData
import com.example.todo_application.R
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems
import com.example.todo_application.repository.Repository
import javax.inject.Inject

class TodoViewModel @Inject constructor(private val todoRepository: Repository) {


    fun getTodoAndTodoList(): LiveData<List<TodoAndTodoList>>{
        return todoRepository.getTodoAndTodoList()
    }

    fun getAllTodo():LiveData<List<Todo>>{
        return todoRepository.getAllTodo()
    }


    fun getAllTodoItem():LiveData<List<TodoItems>>{
        return todoRepository.getAllTodoItem()
    }

    fun getSingleAll(id: Int):LiveData<List<TodoAndTodoList>>{
        return todoRepository.getSingleAll(id)
    }

    fun getSingleTodo(id: Int):LiveData<List<Todo>>{
        return todoRepository.getSingleTodo(id)
    }

    fun getSingleTodoItem(Id: Int):LiveData<List<TodoItems>>{
        return todoRepository.getSingleTodoItem(Id)
    }

    suspend fun insertAllTodo(todo: Todo){
        todoRepository.insertAllTodo(todo)
    }

    suspend fun insertAllTodoItems(todo: TodoItems){
        todoRepository.insertAllTodoItems(todo)
    }

    suspend fun deleteTodoById(todoId: Int): Int{
        return todoRepository.deleteTodoById(todoId)
    }

    suspend fun update(item:TodoItems){
        todoRepository.updateTodo(item)
    }





//
//
//
//    fun getAllTodo(): LiveData<List<Todo>>? {
//        return todoRepository.getAllTodo()
//    }
//
//    fun getSingle(id:Int):LiveData<List<Todo>>?{
//        return todoRepository.getSingleTodo(id)
//    }
//
//    suspend fun saveTodo(item:Todo){
//        todoRepository.saveTodo(item)
//    }
//
//
//    suspend fun deleteTdo(id:Int){
//        todoRepository.deleteTodo(id)
//    }
//
//
//    suspend fun updateTodo(item:Todo){
//        todoRepository.updateUsers(item)
//    }



    private fun displayView(view: View){
        view.visibility = View.VISIBLE
    }
    private fun hideEdit(view: View){
        view.visibility = View.GONE
    }

    fun hideView(emailView: View, passwordView: View){
        emailView.visibility = View.GONE
        passwordView.visibility = View.GONE

    }

    fun loginValidation(view:View, email:String,
                        password:String, emailView:
                        View,
                        passwordView: View, emailEditText:View,
                        passwordEditText: View,
                        context: Context
    ): Boolean {
        //VALIDATION
        return if (email.isEmpty() ) {
            displayView(emailView)
            DrawableCompat.setTint(emailEditText.background, ContextCompat.getColor(context, R.color.wrong))
            DrawableCompat.setTint(passwordEditText.background,ContextCompat.getColor(context, R.color.colorWhite))
            hideEdit(passwordView)
            false
        } else if(password.isEmpty()){
            displayView(passwordView)
            DrawableCompat.setTint(passwordEditText.background,ContextCompat.getColor(context, R.color.wrong))
            DrawableCompat.setTint(emailEditText.background,ContextCompat.getColor(context, R.color.colorWhite))
            hideEdit(emailView)
            false
        }else if (!email.contains('@') || !email.contains('.') || email != "test@gmail.com") {
            hideView(emailView,passwordView)
            displayView(emailView)
            DrawableCompat.setTint(emailEditText.background,ContextCompat.getColor(context, R.color.wrong))

            false
        }else if (password != "password") {
            hideView(emailView,passwordView)
            displayView(passwordView)
            DrawableCompat.setTint(passwordEditText.background,ContextCompat.getColor(context, R.color.wrong))

            false
        }else {
            true
        }

    }


}
