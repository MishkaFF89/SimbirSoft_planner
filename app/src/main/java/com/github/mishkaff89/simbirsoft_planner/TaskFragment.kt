package com.github.mishkaff89.simbirsoft_planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.mishkaff89.simbirsoft_planner.databinding.FragmentTaskBinding
import com.github.mishkaff89.simbirsoft_planner.db.DayEntity
import com.github.mishkaff89.simbirsoft_planner.db.MainViewModel
import com.github.mishkaff89.simbirsoft_planner.db.TaskEntity


class TaskFragment : Fragment() {
    lateinit var binding: FragmentTaskBinding


    private val mainViewModel: MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).dataBase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        binding.tvDate.text = TASK_DATE.toString()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveData()

    }


    private fun saveData(){
        binding.btnAddTask.setOnClickListener {
            val title = binding.edNameTask.text.toString()
            val desc = binding.edDescTask.text.toString()
            val startTime = parseTime(binding.spinnerStartTime.selectedItem.toString())
            val endTime = parseTime(binding.spinnerEndTime.selectedItem.toString())

            if (startTime > endTime){
                binding.cardStartTime.setBackgroundResource(R.color.bg_warning)
                binding.cardEndTime.setBackgroundResource(R.color.bg_warning)
            } else {

                mainViewModel.insertTask(TaskEntity(
                    null,
                    title,
                    desc,
                    startTime,
                    endTime,
                    TASK_DATE!!
                    ))
                findNavController().navigate(R.id.action_taskFragment_to_startFragment)
            }

        }


    }

    private fun parseTime(time: String): Int {
        val hour = time.split(":").first()
        if (hour[0].toString() == "0") {
            hour.drop(1)
        }
        return hour.toInt()
    }


}