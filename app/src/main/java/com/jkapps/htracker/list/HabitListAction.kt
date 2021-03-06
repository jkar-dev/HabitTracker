package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseAction

sealed class HabitListAction : BaseAction {

    sealed class EffectOnHabits : HabitListAction() {
        object GetAllHabits : EffectOnHabits()
        data class TryToChangeDoneUnits(val habit: Habit, val makeUnitDone : Boolean) : EffectOnHabits()
        data class SaveHabit(val habit: Habit) : EffectOnHabits()
        data class DeleteHabit(val habit: Habit) : EffectOnHabits()
    }
    // object DoNothing : HabitListAction() // mock
    object OpenAddEditDialog : HabitListAction()
    object CloseAddEditDialog : HabitListAction()
}