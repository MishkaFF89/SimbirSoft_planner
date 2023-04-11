package com.github.mishkaff89.simbirsoft_planner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mishkaff89.simbirsoft_planner.DATE
import com.github.mishkaff89.simbirsoft_planner.MainApp
import com.github.mishkaff89.simbirsoft_planner.R
import com.github.mishkaff89.simbirsoft_planner.TASK_DATE
import com.github.mishkaff89.simbirsoft_planner.adapter.CalendarAdapter
import com.github.mishkaff89.simbirsoft_planner.databinding.FragmentCalendarBinding
import com.github.mishkaff89.simbirsoft_planner.db.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment(){
    lateinit var binding: FragmentCalendarBinding
    lateinit var adapter: CalendarAdapter

    private val mainViewModel: MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).dataBase)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        TASK_DATE = currentDate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
        observer(TASK_DATE!!)
        binding.tvDateChoose.text = DATE
        selectDate()
        addTask()
    }

    private fun initRc() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = CalendarAdapter()
        rcView.adapter = adapter

    }

    private fun addTask() {
        binding.btnNewTask.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_taskFragment)
        }


    }
    private fun selectDate(){
        val calendar = Calendar.getInstance()
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year,month,dayOfMonth)
            val dateFormatter = SimpleDateFormat("yyMMdd", Locale.getDefault())
            val formatterDate = dateFormatter.format(calendar.time)
            DATE = DateFormat.getDateInstance().format(calendar.time).toString()
            binding.tvDateChoose.text = DATE
            TASK_DATE = formatterDate.toInt()
            observer(TASK_DATE!!)
        }

    }

    private fun currentDate(): Int {
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("yyMMdd", Locale.getDefault())
        DATE = DateFormat.getDateInstance().format(calendar.time).toString()
        return dateFormatter.format(calendar.time).toInt()
    }

   private fun observer(date: Int){
        mainViewModel.getAllTasksForDay(date).observe(viewLifecycleOwner){
            adapter.updateAdapter(it)
        }

   }

}