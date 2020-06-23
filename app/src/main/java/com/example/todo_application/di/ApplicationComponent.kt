package com.example.todo_application.di

import com.example.todo_application.di.modules.DatabaseModule
import com.example.todo_application.screens.SignIn
import com.example.todo_application.screens.MainActivity
import com.example.todo_application.screens.singletodo.SingleTaskFragment
import com.example.todo_application.screens.todo.TodoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: TodoFragment)
    fun inject(fragment: SingleTaskFragment)
    fun inject(fragment: SignIn)



}