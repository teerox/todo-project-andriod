package com.example.todo_application.repository

import androidx.lifecycle.LiveData
import com.example.todo_application.datasource.TodoLocalDataSource
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository  @Inject constructor (private var taskLocalDataSource: TodoLocalDataSource):TodoRepository<Todo>{
    override fun getTodoAndTodoList(): LiveData<List<TodoAndTodoList>> {
       return taskLocalDataSource.getTodoAndTodoList()
    }

    override fun getAllTodo(): LiveData<List<Todo>> {
       return taskLocalDataSource.getAllTodo()
    }

    override fun getAllTodoItem(): LiveData<List<TodoItems>> {
       return taskLocalDataSource.getAllTodoItem()
    }

    override fun getSingleAll(id: Int): LiveData<List<TodoAndTodoList>> {
        return taskLocalDataSource.getSingleAll(id)
    }

    override fun getSingleTodo(id: Int): LiveData<List<Todo>> {
       return taskLocalDataSource.getSingleTodo(id)
    }

    override fun getSingleTodoItem(Id: Int): LiveData<List<TodoItems>> {
        return taskLocalDataSource.getSingleTodoItem(Id)
    }

    override suspend fun insertAllTodo(todo: Todo) {

        withContext(Dispatchers.IO) {
            coroutineScope {
                launch { taskLocalDataSource.insertAllTodo(todo)}
            }
        }

    }

    override suspend fun insertAllTodoItems(todo: TodoItems) {
        withContext(Dispatchers.IO) {
            coroutineScope {
                launch { taskLocalDataSource.insertAllTodoItems(todo)}
            }
        }
    }

    override suspend fun deleteTodoById(todoId: Int): Int {
       return taskLocalDataSource.deleteTodoById(todoId)
    }


//    override fun getAllTodo(): LiveData<List<Todo>>? {
//        return taskLocalDataSource.getAllTodo()
//    }
//
//    override fun getSingleTodo(item: Int): LiveData<List<Todo>>? {
//      return taskLocalDataSource.getSingleTodo(item)
//    }
//
//    override suspend fun saveTodo(item: Todo) {
//        withContext(Dispatchers.IO) {
//            coroutineScope {
//                launch { taskLocalDataSource.saveTodo(item)}
//            }
//        }
//    }
//
//    override suspend fun deleteTodo(item: Int) {
//        withContext(Dispatchers.IO) {
//            coroutineScope {
//                launch { taskLocalDataSource.deleteTodo(item) }
//            }
//        }
//    }
//
//    override suspend fun updateUsers(item: Todo) {
//        withContext(Dispatchers.IO) {
//            coroutineScope {
//                launch { taskLocalDataSource.updateTodo(item)}
//            }
//        }
//    }

    override suspend fun updateTodo(todo: TodoItems) {
        withContext(Dispatchers.IO) {
            coroutineScope {
                launch { taskLocalDataSource.updateTodo(todo)}
            }
        }}
//
//

}