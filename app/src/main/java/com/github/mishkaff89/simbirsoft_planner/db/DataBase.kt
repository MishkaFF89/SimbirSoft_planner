package com.github.mishkaff89.simbirsoft_planner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [TaskEntity::class, DayEntity::class], version = 1)
abstract class DataBase: RoomDatabase(){
    abstract fun getDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDataBase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "DataBase"
                ).build()
                instance
            }
        }
    }
}