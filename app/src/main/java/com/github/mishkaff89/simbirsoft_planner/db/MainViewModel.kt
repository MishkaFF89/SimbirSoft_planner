package com.github.mishkaff89.simbirsoft_planner.db

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(dataBase: DataBase): ViewModel() {
    private val dao = dataBase.getDao()

    //Day
    private suspend fun isDayItemExists(date: Int): Boolean{
        return dao.getAllDay(date).isNotEmpty()
    }


    //Task
    fun insertTask(item: TaskEntity) = viewModelScope.launch{
        dao.insertTask(item)
        if (!isDayItemExists(item.dayId)) dao.insertDay(DayEntity(null,item.dayId))
    }

    fun getAllTasksForDay(dayId: Int): LiveData<List<TaskEntity>>{
        return dao.getAllTasksForDay(dayId).asLiveData()
    }

    class MainViewModelFactory(private val database: DataBase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }

}