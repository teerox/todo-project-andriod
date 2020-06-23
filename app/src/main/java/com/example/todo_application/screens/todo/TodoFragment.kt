package com.example.todo_application.screens.todo


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.MyApplication
import com.example.todo_application.R
import com.example.todo_application.databinding.FragmentTodoBinding
import com.example.todo_application.model.DataPassed
import com.example.todo_application.screens.todoviewmodel.TodoViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TodoFragment : Fragment(),
    CustomDialogueFragment.NoticeDialogListener {
    lateinit var binding: FragmentTodoBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TodoListAdapter


    @Inject
    lateinit var todoViewModel: TodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity().application as MyApplication).getSharedComponent().inject(this)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_todo, container, false)

    binding.addList.setOnClickListener {
        showNoticeDialog()
    }

        recyclerView = binding.todoRecy

        todoViewModel

        todoViewModel.getAllTodo().observe(viewLifecycleOwner, Observer {
            todoList ->
            todoList?.let {
                Log.e("My Result",it.toString())
                adapter = TodoListAdapter(todoList,todoViewModel,requireContext()){
                    todo->
                    val data = DataPassed(todo.todoTitle,todo.todoId)
                    val action = TodoFragmentDirections.actionTodoFragmentToSingleTaskFragment(data)
                    findNavController().navigate(action)
                }
                recyclerView.adapter = adapter

            }
        })
        return binding.root
    }


    private fun showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        val dialog =
            CustomDialogueFragment()
        dialog.show(requireActivity().supportFragmentManager, "CustomDialogueFragment")

    }

    override fun getTodo(title: String, color: String) {
        Toast.makeText(requireContext(),title + color,Toast.LENGTH_LONG).show()
    }


}
