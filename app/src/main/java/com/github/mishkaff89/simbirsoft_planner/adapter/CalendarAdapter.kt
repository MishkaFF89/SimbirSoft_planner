package com.github.mishkaff89.simbirsoft_planner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.simbirsoft_planner.ARRAY_TIME
import com.github.mishkaff89.simbirsoft_planner.R
import com.github.mishkaff89.simbirsoft_planner.TASK_DATE
import com.github.mishkaff89.simbirsoft_planner.databinding.TimeItemBinding
import com.github.mishkaff89.simbirsoft_planner.db.TaskEntity

class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    private val listTime = ARRAY_TIME
    var listEntity = mutableListOf<TaskEntity>()

    class CalendarViewHolder(val binding: TimeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TimeItemBinding.inflate(inflater, parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        with(holder.binding) {

            val time = listTime[position]
            tvTime.text = if (time <= 8) {
                "0$time:00 - 0${time+1}:00"
            } else if(time==9){
                "0$time:00 - ${time+1}:00"
            } else if (time<23){
                "$time:00 - ${time+1}:00"
            } else{
                "$time:00 - 00:00"
            }
            listEntity.forEach {
                if (it.id != null) {
                    if (TASK_DATE == it.dayId) {
                        if (it.startTime == time) {
                            bgTask.visibility = View.VISIBLE
                            bgTask.setBackgroundResource(R.color.bg_task)
                            tvTask.text = it.title
                            tvDesc.text = it.description
                        }
                    }
                }

            }

        }

    }

    override fun getItemCount(): Int {
        return listTime.size
    }

    fun updateAdapter(list: List<TaskEntity>) {
        listEntity.clear()
        listEntity.addAll(list)
        notifyDataSetChanged()

    }




}