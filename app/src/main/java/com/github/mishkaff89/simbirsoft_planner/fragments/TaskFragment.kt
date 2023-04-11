package com.github.mishkaff89.simbirsoft_planner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.mishkaff89.simbirsoft_planner.DATE
import com.github.mishkaff89.simbirsoft_planner.MainApp
import com.github.mishkaff89.simbirsoft_planner.R
import com.github.mishkaff89.simbirsoft_planner.TASK_DATE
import com.github.mishkaff89.simbirsoft_planner.databinding.FragmentTaskBinding
import com.github.mishkaff89.simbirsoft_planner.db.MainViewModel
import com.github.mishkaff89.simbirsoft_planner.db.TaskEntity


class TaskFragment : Fragment() {
    lateinit var binding: FragmentTaskBinding
    var task: TaskEntity? = null

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).dataBase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        binding.tvDate.text = DATE
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveData()

    }


    private fun saveData() {
        binding.btnAddTask.setOnClickListener {
            val title = binding.edNameTask.text.toString()
            val desc = binding.edDescTask.text.toString()
            val startTime = parseTime(binding.spinnerStartTime.selectedItem.toString())
            val endTime = binding.spinnerStartTime.selectedItem.toString()


            if (title.isNotEmpty() && desc.isNotEmpty()){
                mainViewModel.insertTask(TaskEntity(
                    null,
                    title,
                    desc,
                    startTime,
                    endTime,
                    TASK_DATE!!
                ))

                findNavController().navigate(R.id.action_taskFragment_to_startFragment)
            } else {
                when{
                    title.isEmpty() -> binding.edNameTask.setBackgroundResource(R.color.bg_warning)
                    desc.isEmpty() -> binding.edDescTask.setBackgroundResource(R.color.bg_warning)
                }
            }


        }

    }


    private fun parseTime(time: String): Int {
        val hour = time.split(":")[0]

        if (hour.first().equals("0")) {
            hour.drop(1)
        }
        return hour.toInt()
    }
}


