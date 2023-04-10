package com.github.mishkaff89.simbirsoft_planner.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_entity")
data class DayEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo
    val dayId: Int
)