package com.jkapps.htracker.domain

import com.jkapps.htracker.domain.entity.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun getAllHabits() : Flow<List<Habit>>
    suspend fun saveHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
}