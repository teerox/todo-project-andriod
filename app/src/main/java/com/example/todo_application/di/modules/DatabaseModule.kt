package com.example.todo_application.di.modules

import android.content.Context
import androidx.room.Room
import com.example.todo_application.MyApplication
import com.example.todo_application.database.TodoDAO
import com.example.todo_application.database.TodoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: MyApplication){
    @Provides
    fun context(): Context {
        return application
    }
    @Singleton
    @Provides
    internal fun provideRoomDatabase(context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase ::class.java, "todoDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: TodoDatabase): TodoDAO{
        return database.todoDao()
    }

}