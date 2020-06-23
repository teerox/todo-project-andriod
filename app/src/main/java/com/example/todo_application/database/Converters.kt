package com.example.todo_application.database

//import androidx.room.TypeConverter
//import com.example.todo_application.model.TodoItems
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//class Converters {
//
//
//
//    @TypeConverter
//    fun stringToArray(data:String): List<TodoItems> {
//       return Gson().fromJson(data, Array<TodoItems>::class.java).toList()
//    }
//
//    @TypeConverter
//    fun arrayToString(data:List<TodoItems>):String?{
//        return Gson().toJson(data)
//    }
//}