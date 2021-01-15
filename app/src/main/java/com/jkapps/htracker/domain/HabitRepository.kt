package com.jkapps.htracker.domain

import com.jkapps.htracker.domain.entity.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    val allHabits : Flow<List<Habit>>
    suspend fun saveHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
}