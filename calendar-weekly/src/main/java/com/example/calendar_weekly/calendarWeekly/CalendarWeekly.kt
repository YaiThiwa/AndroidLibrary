package com.example.calendar_weekly.calendarWeekly

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.calendar_weekly.R
import com.example.calendar_weekly.calendarWeekly.model.CalendarWeeklyModel
import com.example.calendar_weekly.databinding.CalendarWeeklyBinding
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class CalendarWeekly: LinearLayout, CalendarWeeklyListener {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0 ): super(context, attrs, defStyleAttr)

    private var binding: CalendarWeeklyBinding = CalendarWeeklyBinding.bind(LayoutInflater.from(context).inflate(R.layout.calendar_weekly, this, false))
    private var adapter: CalendarWeeklyAdapter
    private var list = ArrayList<CalendarWeeklyModel.DayOfWeekly>()
    private val countDayOfWeekly = 7
    private var lastWeekCount = 1
    private var nextWeekCount = 1
    private var dateNow: Date
    private var currentPage = 1
    var clickDay: ((Long) -> Unit)? = null
    init {
        addView(binding.root)
        lastWeekCount = 1
        nextWeekCount = 1
        dateNow = Date()
        list.add(getDateOfCurrentWeek(getTimestampOfFirstDay(getDayOfLastWeek(dateNow.time))))
        val currentWeek = getDateOfCurrentWeek(getTimestampOfFirstDay(dateNow))
        list.add(currentWeek)
        list.add(getDateOfCurrentWeek(getTimestampOfFirstDay(getNextWeek(dateNow.time))))
        findSundayForSetTitle(currentWeek)
        adapter = CalendarWeeklyAdapter(list, this)
        binding.viewPagerDay.also {
            it.adapter = adapter
            it.isUserInputEnabled = false
            it.setCurrentItem(currentPage, false)
        }

        binding.buttonPrevious.setOnClickListener {
            if(currentPage > 0) {
                currentPage--
                if(currentPage == 0) {
                    onClickPrevious()
                } else {
                    binding.viewPagerDay.setCurrentItem(currentPage, false)
                }
                findSundayForSetTitle(list[currentPage])
            }
        }

        binding.buttonNext.setOnClickListener {
            if(currentPage < list.size-1) {
                currentPage++
                if(currentPage == list.size-1){
                    onClickNext()
                } else {
                    binding.viewPagerDay.setCurrentItem(currentPage, false)
                }
                findSundayForSetTitle(list[currentPage])
            }
        }
    }

    private fun findSundayForSetTitle(dayOfWeekly: CalendarWeeklyModel.DayOfWeekly) {
        setTitle(dayOfWeekly.sunday.date)
    }

    private fun setTitle(date: Long) {
        binding.dateTitle.text = SimpleDateFormat("MMMM yyyy").format(date)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onClickPrevious() {
        lastWeekCount++
        val mList = ArrayList<CalendarWeeklyModel.DayOfWeekly>()
        mList.add(getDateOfCurrentWeek(getTimestampOfFirstDay(getDayOfLastWeek(dateNow.time))))
        mList.addAll(list)
        list = mList
        adapter.list = list
        adapter.notifyDataSetChanged()
        currentPage++
        binding.viewPagerDay.setCurrentItem(currentPage, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onClickNext() {
        nextWeekCount++
        list.add(getDateOfCurrentWeek(getTimestampOfFirstDay(getNextWeek(dateNow.time))))
        adapter.list = list
        adapter.notifyDataSetChanged()
        binding.viewPagerDay.setCurrentItem(currentPage, false)
    }

    private fun getDayOfLastWeek(timestamp: Long): Date {
        return Date(timestamp - (86400000L * (countDayOfWeekly*lastWeekCount)))
    }

    private fun getNextWeek(timestamp: Long): Date {
        return Date(timestamp + (86400000L * (countDayOfWeekly*nextWeekCount)))
    }

    private fun getTimestampOfFirstDay(date: Date): Long {
        return date.time - (86400000L * calculatePositionOfDay(getCurrentDay(date)))
    }

    private fun getCurrentDay(dateNow: Date): Int {
        val cal = Calendar.getInstance()
        cal.time = dateNow
        return cal.get(Calendar.DAY_OF_WEEK)
    }

    private fun calculatePositionOfDay(day: Int): Int {
        return if(day == 1)  0  else  day - 1
    }

    private fun getDateOfCurrentWeek(stampOfFirstDay: Long): CalendarWeeklyModel.DayOfWeekly {
        val sunday = Date(stampOfFirstDay)
        val monday = Date(stampOfFirstDay + 86400000L)
        val tuesday = Date(stampOfFirstDay + (86400000L*2))
        val wednesday = Date(stampOfFirstDay+ (86400000L*3))
        val thursday = Date(stampOfFirstDay + (86400000L*4))
        val friday = Date(stampOfFirstDay + (86400000L*5))
        val saturday = Date(stampOfFirstDay + (86400000L*6))

        return CalendarWeeklyModel.DayOfWeekly(
            CalendarWeeklyModel.Day(stampOfFirstDay, false, day(sunday), dateNow.time == sunday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + 86400000L, false, day(monday), dateNow.time == monday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + (86400000L*2), false, day(tuesday), dateNow.time == tuesday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + (86400000L*3), false, day(wednesday), dateNow.time == wednesday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + (86400000L*4), false, day(thursday), dateNow.time == thursday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + (86400000L*5), false, day(friday), dateNow.time == friday.time),
            CalendarWeeklyModel.Day(stampOfFirstDay + (86400000L*6), false, day(saturday), dateNow.time == saturday.time)
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun day(date: Date): String {
        return SimpleDateFormat("d").format(date)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClickDay(day: CalendarWeeklyModel.Day) {
        setTitle(day.date)
        list.forEach {
            it.sunday.isSelected = it.sunday.date == day.date
            it.monday.isSelected = it.monday.date == day.date
            it.tuesday.isSelected = it.tuesday.date == day.date
            it.wednesday.isSelected = it.wednesday.date == day.date
            it.thursday.isSelected = it.thursday.date == day.date
            it.friday.isSelected = it.friday.date == day.date
            it.saturday.isSelected = it.saturday.date == day.date
        }
        adapter.list = list
        adapter.notifyDataSetChanged()

        clickDay?.invoke(day.date)
    }
}