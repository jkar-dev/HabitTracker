package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseAction

sealed class HabitListAction : BaseAction {
    object Nothing : HabitListAction() // mock
    object OpenAddEditDialog : HabitListAction()
    object CloseAddEditDialog : HabitListAction()
    data class MakeUnitDone(val habit: Habit) : HabitListAction()
    data class MakeUnitUndone(val habit: Habit) : HabitListAction()
    data class SaveHabit(val habit: Habit) : HabitListAction()
}