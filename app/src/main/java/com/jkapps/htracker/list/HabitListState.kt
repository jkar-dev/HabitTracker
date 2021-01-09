package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.entity.Progress
import com.jkapps.htracker.domain.mvi.BaseState

data class HabitListState(
    val habits : List<Habit>,
    val isDialogShowing : Boolean
) : BaseState {
    val progress : Progress get() {
        var sumProgress = 0f
        habits.forEach {
            sumProgress += it.progressFraction
        }
        val result = sumProgress/habits.size * 100
        return Progress(result.toInt())
    }
}