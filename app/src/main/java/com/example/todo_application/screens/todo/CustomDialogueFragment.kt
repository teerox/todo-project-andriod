package com.example.todo_application.screens.todo

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.todo_application.R


class CustomDialogueFragment : DialogFragment(){
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    private lateinit var listener: NoticeDialogListener
    lateinit var myTitle: EditText
    lateinit var myColor: Spinner

    interface NoticeDialogListener {
        fun getTodo(title:String,color:String)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;
        val view = inflater.inflate(R.layout.customdialogue, null)
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
            // Add action buttons
            .setPositiveButton("Save",
                DialogInterface.OnClickListener { _, _ ->

                    val title = myTitle.text.toString()
                    val selected = myColor.selectedItem.toString()
                    listener.getTodo(title,selected)

                })
            .setNegativeButton("cancel",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })

        myTitle = view.findViewById(R.id.title)
        myColor = view.findViewById(R.id.color_spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.color,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            myColor.adapter = adapter
        }

            val dialogue = builder.create()
            dialogue.setCanceledOnTouchOutside(false)

        return dialogue
        }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }
}