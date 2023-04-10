package com.github.mishkaff89.simbirsoft_planner

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.github.mishkaff89.simbirsoft_planner.databinding.ClickDialogBinding
import com.github.mishkaff89.simbirsoft_planner.db.TaskEntity

object ContextDialog {
    fun showDialog(context: Context,taskEntity: TaskEntity){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = ClickDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            tvDateClick.text = taskEntity.dayId.toString()
            tvTitleClick.text = taskEntity.title
            tvDescClick.text = taskEntity.description
            tvTimeClick.text = "${taskEntity.startTime} - ${taskEntity.endTime}"

        }

        dialog = builder.create()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }


}