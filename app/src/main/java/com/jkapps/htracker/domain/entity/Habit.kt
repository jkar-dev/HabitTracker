package com.jkapps.htracker.domain.entity

data class Habit(
    val title : String,
    val subtitle : String = "",
    val timesPerDay : Int,
    val doneUnits : Int = 0
) {
    val isComplete : Boolean = doneUnits == timesPerDay
    val progressFraction : Float = doneUnits/timesPerDay.toFloat()
}