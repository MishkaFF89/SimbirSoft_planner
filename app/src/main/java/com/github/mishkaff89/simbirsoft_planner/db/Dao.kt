package com.github.mishkaff89.simbirsoft_planner.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    //Day
    @Insert
    suspend fun insertDay(dayEntity: DayEntity)

    @Query("SELECT * FROM day_entity WHERE dayId LIKE :dayId")
    suspend fun getAllDay(dayId: Int): List<DayEntity>


    //Task
    @Insert
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM task WHERE dayId LIKE :dayId")
    fun getAllTasksForDay(dayId: Int): Flow<List<TaskEntity>>


}