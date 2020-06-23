package com.example.todo_application.screens.singletodo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.MyApplication
import com.example.todo_application.R
import com.example.todo_application.databinding.FragmentSingleTaskBinding
import com.example.todo_application.model.TodoItems
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject



/**
 * A simple [Fragment] subclass.
 */
class SingleTaskFragment : Fragment() {

    lateinit var binding: FragmentSingleTaskBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SingleTodoListAdapter
    var arr = arrayListOf<TodoItems>()

    var undone = 0
    @Inject
    lateinit var todoViewModel: TodoViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity().application as MyApplication).getSharedComponent().inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_single_task, container, false)


        recyclerView = binding.singleRecyc
        val args = SingleTaskFragmentArgs.fromBundle(requireArguments()).data
        binding.tasktitle.text = args.todoTitle

        todoViewModel.getSingleTodoItem(args.id).observe(viewLifecycleOwner, Observer  {
                todoList -> todoList?.let {
            adapter = SingleTodoListAdapter(it,todoViewModel)

           var numberOfTask =  it.size
            var done = 0
            it.forEach { item ->

                if (item.completed){
                done += 1
            }
                binding.taskNumber.text = "completed $done of $numberOfTask tasks"
            }



            adapter.notifyDataSetChanged()
        }
            recyclerView.adapter = adapter
        })

        binding.close.setOnClickListener {
         dialogue(args.id)
        }

        binding.goBack.setOnClickListener {
            val action =  SingleTaskFragmentDirections.actionSingleTaskFragmentToTodoFragment()
            findNavController().navigate(action)
        }

//        todoViewModel.getSingleAll(args.id).observe(viewLifecycleOwner, Observer {
//            todoList -> todoList?.let {
//            taskList ->
//            Log.e("My Id",taskList.toString())
//            binding.tasktitle.text = taskList[0].todo.todoTitle
//        }
//        })

        binding.fab.setOnClickListener {
            showNoticeDialog(args.id)
        }

        return binding.root
    }


    private fun showNoticeDialog(id:Int) {
        // Create an instance of the dialog fragment and show it
        val dialog =
            SingleCustomDialogueFragment()
        dialog.mid = id
        dialog.show(requireActivity().supportFragmentManager, "CustomDialogueFragment")

    }

    private fun dialogue(id: Int){
        AlertDialog.Builder(context)
            .setTitle("Delete entry")
            .setMessage("Are you sure you want to delete this Todo?") // Specifying a listener allows you to take an action before dismissing the dialog.
// The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialog, which ->
                    GlobalScope.launch {
                        todoViewModel.deleteTodoById(id)
                    }
                    val action =  SingleTaskFragmentDirections.actionSingleTaskFragmentToTodoFragment()
                    findNavController().navigate(action)

                    // Continue with delete operation
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

}
