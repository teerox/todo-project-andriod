package com.example.todo_application.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.todo_application.MyApplication
import com.example.todo_application.R
import com.example.todo_application.databinding.ActivityMainBinding
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.singletodo.SingleCustomDialogueFragment
import com.example.todo_application.screens.todo.CustomDialogueFragment
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CustomDialogueFragment.NoticeDialogListener,
    SingleCustomDialogueFragment.NoticeSingleDialogListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @Inject
    lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as MyApplication).getSharedComponent().inject(this)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navController = this.findNavController(R.id.navHost)
       // NavigationUI.setupActionBarWithNavController(this, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    //save to db
    override fun getTodo(title: String, color: String) {
        saveAllTodo(title,color)
    }


    private fun saveAllTodo(title: String, color: String){
        GlobalScope.launch {
            val item = Todo(todoTitle = title,color = color)
            todoViewModel.insertAllTodo(item)
        }
    }

//    override fun getSingleTodo(title: String, color: String, task: String, time: String,id:Int) {
//            GlobalScope.launch {
//            val item = TodoItems(item = )
//                val send = Todo(title,color, listOf(item))
//                send.id = id
//                todoViewModel.updateTodo(send)
//
//        }
//    }


    fun viewData(){
        todoViewModel.getAllTodo().observeForever {
            todoList ->
            todoList?.let {
                Log.e("My DataBase",it.toString())
            }
        }
    }

    override fun saveAllTodoItem(task: String, time: String, id: Int) {
            GlobalScope.launch {
            val item = TodoItems(item = task,listItemId = id,date = time)
//                val send = Todo(title,color, listOf(item))
//                send.id = id
                todoViewModel.insertAllTodoItems(item)

        }
    }
}
