package com.example.calendar_weekly.calendarWeekly

import com.example.calendar_weekly.calendarWeekly.model.CalendarWeeklyModel

interface CalendarWeeklyListener {
    fun onClickDay(day: CalendarWeeklyModel.Day)
}