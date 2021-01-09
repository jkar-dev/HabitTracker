package com.jkapps.htracker.data

import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.domain.entity.Habit

class HabitRepositoryImpl : HabitRepository {
    private val habits = listOf(
        Habit("Английский", "", 5, 2),
        Habit("Английский", "Приложение", 5, 5),
        Habit("Английский", "Приложение", 3, 0),
        Habit("Английский", "Приложение", 1, 0))

    override fun getAllHabits(): List<Habit> {
        return habits
    }
}