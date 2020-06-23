package com.example.todo_application

import android.app.Application
import com.example.todo_application.di.ApplicationComponent
import com.example.todo_application.di.DaggerApplicationComponent
import com.example.todo_application.di.modules.DatabaseModule


class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }

    fun getSharedComponent(): ApplicationComponent {
        return component
    }


}