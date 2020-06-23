package com.example.todo_application.screens.singletodo

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.databinding.CardBinding
import com.example.todo_application.databinding.SingletaskwithdateBinding
import com.example.todo_application.model.Todo
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SingleTodoListAdapter(private var myArrayList: List<TodoItems>, var todoViewModel: TodoViewModel): RecyclerView.Adapter<SingleTodoListAdapter.ViewHolder>()
{

     class ViewHolder(var binding: SingletaskwithdateBinding,var todoViewModel: TodoViewModel):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item:TodoItems){
            binding.singletask = item

            binding.checked.setOnClickListener {
                if (binding.checked.isChecked){
                    crossText(binding.textChecked)
                    binding.checked.visibility = View.GONE
                    item.completed = true
                    runBlocking {
                        todoViewModel.update(item)
                    }
                }

                }

            if(item.completed){
                crossText(binding.textChecked)
                binding.checked.visibility = View.GONE

            }


        }

        private fun crossText(textView: TextView){
            // textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            textView.setTextColor(Color.parseColor("#FF0000"))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SingletaskwithdateBinding.inflate(view)
        return ViewHolder(
            binding,todoViewModel
        )
    }

    override fun getItemCount(): Int {
        return myArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myArrayList[position])

//        val checkBox = holder.binding.checked
//
//        checkBox.setOnClickListener {
//            if (checkBox.isChecked){
//                holder.crossText(holder.binding.textChecked)
//                holder.binding.checked.visibility = View.GONE
//                myArrayList[position].completed = true
//                runBlocking {
//                    todoViewModel.update(myArrayList[position])
//                }
//            }
//        }
//
//
//    }
//
}
