package com.github.mishkaff89.simbirsoft_planner

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mishkaff89.simbirsoft_planner.databinding.FragmentCalendarBinding
import com.github.mishkaff89.simbirsoft_planner.db.MainViewModel
import com.github.mishkaff89.simbirsoft_planner.db.TaskEntity
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
        binding.tvDateChoose.text = currentDate().toString()
        TASK_DATE = currentDate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
        observer(TASK_DATE!!)
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
            TASK_DATE

        }


    }
    private fun selectDate(){
        val calendar = Calendar.getInstance()
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year,month,dayOfMonth)
            val dateFormatter = SimpleDateFormat("yyMMdd", Locale.getDefault())
            val formatterDate = dateFormatter.format(calendar.time)
            binding.tvDateChoose.text = formatterDate
            TASK_DATE = formatterDate.toInt()
            observer(TASK_DATE!!)
        }

    }

    private fun currentDate(): Int {
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("yyMMdd", Locale.getDefault())
        return dateFormatter.format(calendar.time).toInt()
    }

    private fun observer(date: Int){
        mainViewModel.getAllTasksForDay(date).observe(viewLifecycleOwner){
            adapter.updateAdapter(it)

        }
    }


}