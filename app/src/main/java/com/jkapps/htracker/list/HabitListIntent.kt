package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseIntent

sealed class HabitListIntent : BaseIntent {
    object InitialHabits : HabitListIntent()
    object OnFabClick : HabitListIntent()
    object OnDialogDismiss : HabitListIntent()
    data class OnSaveHabit(val habit: Habit) : HabitListIntent()
    // data class OnCardClick(val habit : Habit) : HabitListIntent()
    data class OnLongCardClick(val habit : Habit) : HabitListIntent()
    data class OnCircleClick(val habit : Habit) : HabitListIntent()
    data class OnLongCircleClick(val habit : Habit) : HabitListIntent()
}