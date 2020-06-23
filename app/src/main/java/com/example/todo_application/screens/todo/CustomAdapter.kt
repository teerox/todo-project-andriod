package com.example.todo_application.screens.todo

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.R
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomAdapter(private var myArray: List<TodoItems>?,private val todoViewModel: TodoViewModel): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View,val todoViewModel: TodoViewModel): RecyclerView.ViewHolder(view) {

        private lateinit var singleTaskCheck: CheckBox
        lateinit var singleTaskText: TextView
        lateinit var linearLayout: LinearLayout


        fun setItem(item:TodoItems){
            singleTaskCheck = itemView.findViewById(R.id.singleTaskCheckbox)
            singleTaskText = itemView.findViewById(R.id.singleTaskText)
            singleTaskText.text = item.item
            singleTaskCheck.isChecked = item.completed
            linearLayout = itemView.findViewById(R.id.layoutCross)

            singleTaskCheck.setOnClickListener{
                if (singleTaskCheck.isChecked){
                    crossText(singleTaskText)
                    singleTaskCheck.visibility = View.GONE
                    item.completed = true
                  //  val update = TodoItems(item = item.item,listItemId = item.listItemId,date = item.date,completed = true)
                    GlobalScope.launch {
                        todoViewModel.update(item)
                    }
                }
            }

            if(item.completed){
                crossText(singleTaskText)
                singleTaskCheck.visibility = View.GONE
            }

        }

        private fun crossText(textView: TextView){
            // textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
           // textView.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singletask,parent,false)
        return CustomViewHolder(view,todoViewModel)
    }

    override fun getItemCount(): Int {
        return myArray!!.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = myArray!![position]
        holder.itemView.context
        holder.setItem(item)



    }


}