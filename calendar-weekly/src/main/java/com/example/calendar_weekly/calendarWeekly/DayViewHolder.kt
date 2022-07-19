package com.example.calendar_weekly.calendarWeekly

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar_weekly.databinding.ViewHolderDayBinding
import com.example.calendar_weekly.R
import com.example.calendar_weekly.calendarWeekly.model.CalendarWeeklyModel

class DayViewHolder(itemView: View, private val listener: CalendarWeeklyListener): RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    private var binding = ViewHolderDayBinding.bind(itemView)
    private lateinit var mDayOfWeekly: CalendarWeeklyModel.DayOfWeekly

    @SuppressLint("SimpleDateFormat")
    fun bindView(dayOfWeekly: CalendarWeeklyModel.DayOfWeekly) {
        mDayOfWeekly = dayOfWeekly
        binding.sunday.text = dayOfWeekly.sunday.day
        binding.monday.text = dayOfWeekly.monday.day
        binding.tuesday.text = dayOfWeekly.tuesday.day
        binding.wednesday.text = dayOfWeekly.wednesday.day
        binding.thursday.text = dayOfWeekly.thursday.day
        binding.friday.text = dayOfWeekly.friday.day
        binding.saturday.text = dayOfWeekly.saturday.day

        binding.imageSelectedSunday.visibility = View.INVISIBLE
        binding.imageSelectedMonday.visibility = View.INVISIBLE
        binding.imageSelectedTuesday.visibility = View.INVISIBLE
        binding.imageSelectedWednesday.visibility = View.INVISIBLE
        binding.imageSelectedThursday.visibility = View.INVISIBLE
        binding.imageSelectedFriday.visibility = View.INVISIBLE
        binding.imageSelectedSaturday.visibility = View.INVISIBLE

        binding.sundayIsNow.visibility = View.INVISIBLE
        binding.mondayIsNow.visibility = View.INVISIBLE
        binding.tuesdayIsNow.visibility = View.INVISIBLE
        binding.wednesdayIsNow.visibility = View.INVISIBLE
        binding.thursdayIsNow.visibility = View.INVISIBLE
        binding.fridayIsNow.visibility = View.INVISIBLE
        binding.saturdayIsNow.visibility = View.INVISIBLE

        binding.sunday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.monday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.tuesday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.wednesday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.thursday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.friday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
        binding.saturday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))

        when {
            dayOfWeekly.sunday.isNow -> binding.sundayIsNow.visibility = View.VISIBLE
            dayOfWeekly.monday.isNow -> binding.mondayIsNow.visibility = View.VISIBLE
            dayOfWeekly.tuesday.isNow -> binding.tuesdayIsNow.visibility = View.VISIBLE
            dayOfWeekly.wednesday.isNow -> binding.wednesdayIsNow.visibility = View.VISIBLE
            dayOfWeekly.thursday.isNow -> binding.thursdayIsNow.visibility = View.VISIBLE
            dayOfWeekly.friday.isNow -> binding.fridayIsNow.visibility = View.VISIBLE
            dayOfWeekly.saturday.isNow -> binding.saturdayIsNow.visibility = View.VISIBLE
        }

        when {
            dayOfWeekly.sunday.isSelected -> {
                binding.imageSelectedSunday.visibility = View.VISIBLE
                binding.sunday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.monday.isSelected -> {
                binding.imageSelectedMonday.visibility = View.VISIBLE
                binding.monday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.tuesday.isSelected -> {
                binding.imageSelectedTuesday.visibility = View.VISIBLE
                binding.tuesday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.wednesday.isSelected -> {
                binding.imageSelectedWednesday.visibility = View.VISIBLE
                binding.wednesday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.thursday.isSelected -> {
                binding.imageSelectedThursday.visibility = View.VISIBLE
                binding.thursday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.friday.isSelected -> {
                binding.imageSelectedFriday.visibility = View.VISIBLE
                binding.friday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            dayOfWeekly.saturday.isSelected -> {
                binding.imageSelectedSaturday.visibility = View.VISIBLE
                binding.saturday.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
        }

        binding.buttonSunday.setOnClickListener(this)
        binding.buttonMonday.setOnClickListener(this)
        binding.buttonTuesday.setOnClickListener(this)
        binding.buttonWednesday.setOnClickListener(this)
        binding.buttonThursday.setOnClickListener(this)
        binding.buttonFriday.setOnClickListener(this)
        binding.buttonSaturday.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.buttonSunday -> listener.onClickDay(mDayOfWeekly.sunday)
            binding.buttonMonday -> listener.onClickDay(mDayOfWeekly.monday)
            binding.buttonTuesday -> listener.onClickDay(mDayOfWeekly.tuesday)
            binding.buttonWednesday -> listener.onClickDay(mDayOfWeekly.wednesday)
            binding.buttonThursday -> listener.onClickDay(mDayOfWeekly.thursday)
            binding.buttonFriday -> listener.onClickDay(mDayOfWeekly.friday)
            binding.buttonSaturday -> listener.onClickDay(mDayOfWeekly.saturday)
        }
    }
}