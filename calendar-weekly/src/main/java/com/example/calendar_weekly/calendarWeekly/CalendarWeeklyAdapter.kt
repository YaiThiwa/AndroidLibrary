package com.example.calendar_weekly.calendarWeekly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar_weekly.R
import com.example.calendar_weekly.calendarWeekly.model.CalendarWeeklyModel

class CalendarWeeklyAdapter(var list: ArrayList<CalendarWeeklyModel.DayOfWeekly>, private val listener: CalendarWeeklyListener): RecyclerView.Adapter<DayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_day, parent, false), listener)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}