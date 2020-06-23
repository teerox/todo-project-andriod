package com.example.todo_application.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize


@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)var todoId:Int = 0,
    @ColumnInfo(name = "todoTitle")
    val todoTitle:String?,
    @ColumnInfo(name = "color")
    val color:String?
)

@Entity
data class TodoItems(
    @PrimaryKey(autoGenerate = true) var todoListId:Int = 0,
    var completed:Boolean = false,
    val item:String,
    val date:String,
    val listItemId: Int){
//    @IgnoredOnParcel
  //  @PrimaryKey(autoGenerate = true)var id:Int = 0
}


data class TodoAndTodoList(
    @Embedded val todo: Todo,
    @Relation(
        parentColumn = "todoId",
        entityColumn = "listItemId"
    )
    val todoList: TodoItems?
)


@Parcelize
data class DataPassed( val todoTitle:String?,val id:Int):Parcelable