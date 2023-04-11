package com.github.mishkaff89.simbirsoft_planner

import android.app.Application
import com.github.mishkaff89.simbirsoft_planner.db.DataBase
class MainApp: Application() {
    val dataBase by lazy {DataBase.getDataBase(this)}
}