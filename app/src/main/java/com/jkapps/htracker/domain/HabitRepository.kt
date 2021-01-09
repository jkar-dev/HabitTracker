package com.jkapps.htracker.domain

import com.jkapps.htracker.domain.entity.Habit

interface HabitRepository {
    fun getAllHabits() : List<Habit>
}