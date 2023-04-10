package com.github.mishkaff89.simbirsoft_planner.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "task")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val description: String,

    @ColumnInfo
    val startTime: Int,

    @ColumnInfo
    val endTime: Int,

    @ColumnInfo
    val dayId: Int
)