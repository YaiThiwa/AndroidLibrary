package com.example.calendar_weekly.calendarWeekly.model

class CalendarWeeklyModel {

    data class DayOfWeekly(
        val sunday: Day,
        val monday: Day,
        val tuesday: Day,
        val wednesday: Day,
        val thursday: Day,
        val friday: Day,
        val saturday: Day
    )

    data class Day(
        val date: Long,
        var isSelected: Boolean,
        val day: String,
        val isNow: Boolean
    )
}
