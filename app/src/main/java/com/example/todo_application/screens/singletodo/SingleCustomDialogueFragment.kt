package com.example.todo_application.screens.singletodo

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.todo_application.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SingleCustomDialogueFragment : DialogFragment(){
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    private lateinit var listener: NoticeSingleDialogListener
//    lateinit var myTitle: EditText
//    lateinit var myColor: Spinner
    lateinit var myTodo: EditText

     var mid:Int = 0

    interface NoticeSingleDialogListener {
        fun saveAllTodoItem(task:String,time:String,id:Int)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;
        val view = inflater.inflate(R.layout.customsingledialogue, null)
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
            // Add action buttons
            .setPositiveButton("Save",
                DialogInterface.OnClickListener { _, _ ->
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatted = current.format(formatter)
//                    val title = myTitle.text.toString()
//                    val selected = myColor.selectedItem.toString()
                    val task = myTodo.text.toString()

                    listener.saveAllTodoItem(task,formatted,mid)

                })
            .setNegativeButton("cancel",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })

//        myTitle = view.findViewById(R.id.title2)
//        myColor = view.findViewById(R.id.color_spinner2)
        myTodo = view.findViewById(R.id.title2)
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.color,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            myColor.adapter = adapter
//        }

            val dialogue = builder.create()
            dialogue.setCanceledOnTouchOutside(false)

        return dialogue
        }
    //?: throw IllegalStateException("Activity cannot be null")


       // return builder.create()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeSingleDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeSingleDialogListener"))
        }
    }
}