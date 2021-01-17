package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseState

data class HabitListState(
    val habits : List<Habit>,
    val isDialogShowing : Boolean
) : BaseState {

    val progressPercentage : Int get() {
        if (habits.isEmpty()) return 0

        var sumProgress = 0f
        habits.forEach { sumProgress += it.progressFraction }
        val result = sumProgress/ habits.size * 100
        return result.toInt()
    }

    companion object {
        val from = HabitListState(emptyList(), false)
    }
}