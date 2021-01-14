package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseResult

sealed class HabitListResult : BaseResult {
    data class NewHabitList(val habits : List<Habit>) : HabitListResult()
    object OpenDialog : HabitListResult()
    object CloseDialog : HabitListResult()
}