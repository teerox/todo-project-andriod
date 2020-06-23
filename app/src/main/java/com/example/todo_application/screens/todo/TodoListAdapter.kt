package com.example.todo_application.screens.todo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.databinding.CardBinding
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoAndTodoList
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.singletodo.SingleTaskFragmentDirections
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoListAdapter(var myArray: List<Todo>, private val todoViewModel: TodoViewModel, val context: Context,private val clickListener: (result: Todo) -> Unit): RecyclerView.Adapter<TodoListAdapter.ViewHolder>()
{

    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    private val arr = arrayListOf<TodoItems>()

    class ViewHolder(var binding: CardBinding,val todoViewModel: TodoViewModel,val context: Context):
        RecyclerView.ViewHolder(binding.root) {
        lateinit var recyclerView: RecyclerView



        fun bind(item:Todo,context:Context, clickListener: (result: Todo) -> Unit){
            recyclerView = binding.recyc
            when(item.color){
                "Magenta" -> binding.cardView.setCardBackgroundColor(Color.MAGENTA)
                "Blue" -> binding.cardView.setCardBackgroundColor(Color.BLUE)
                "Green" -> binding.cardView.setCardBackgroundColor(Color.GREEN)
                "Cyan" -> binding.cardView.setCardBackgroundColor(Color.CYAN)
                "Gray" -> binding.cardView.setCardBackgroundColor(Color.GRAY)
            }
            binding.todoList = item
            binding.root.setOnClickListener {
                clickListener(item)
            }

//            binding.delete.setOnClickListener {
//                   dialogue(item.todoId,context)
//            }


        }

        private fun dialogue(id: Int,context:Context){
            AlertDialog.Builder(context)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this Todo?") // Specifying a listener allows you to take an action before dismissing the dialog.
// The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, which ->
                        GlobalScope.launch {
                            todoViewModel.deleteTodoById(id)
                        }


                        // Continue with delete operation
                    }) // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(view)
        return ViewHolder(
            binding,todoViewModel,context
        )
    }

    override fun getItemCount(): Int {
        return myArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = myArray[position]
        holder.bind(myArray[position],context, clickListener)

        val myLayout = LinearLayoutManager(holder.recyclerView.context,
            LinearLayoutManager.VERTICAL,false)

       val num = item.todoId

        todoViewModel.getSingleTodoItem(num).observeForever {
            myLayout.initialPrefetchItemCount = it.size
            val  innerAdapter = CustomAdapter(it,todoViewModel)
            holder.recyclerView.layoutManager = myLayout
            holder.recyclerView.adapter = innerAdapter
        }

        holder.recyclerView.setRecycledViewPool(viewPool)

    }




}
