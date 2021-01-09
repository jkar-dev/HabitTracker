package com.jkapps.htracker.list

import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.domain.mvi.BaseEvent

sealed class HabitListEvent : BaseEvent {
    object OnFabClick : HabitListEvent()
    object OnDialogDismiss : HabitListEvent()
    data class OnSaveHabit(val habit: Habit) : HabitListEvent()
    data class OnCardClick(val habit : Habit) : HabitListEvent()
    data class OnLongCardClick(val habit : Habit) : HabitListEvent()
    data class OnCircleClick(val habit : Habit) : HabitListEvent()
    data class OnLongCircleClick(val habit : Habit) : HabitListEvent()
}