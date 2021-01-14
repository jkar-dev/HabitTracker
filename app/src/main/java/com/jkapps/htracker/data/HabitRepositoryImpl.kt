package com.jkapps.htracker.data

import com.jkapps.htracker.data.local.HabitDao
import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.utils.toDomain
import com.jkapps.utils.toRoomEntity
import kotlinx.coroutines.flow.*

class HabitRepositoryImpl(private val habitDao: HabitDao) : HabitRepository {

    override suspend fun getAllHabits(): Flow<List<Habit>> {
        return habitDao.getAllHabits().map { it.map { habitEntity -> habitEntity.toDomain() } }
    }

    override suspend fun saveHabit(habit: Habit) {
        habitDao.insertHabit(habit.toRoomEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toRoomEntity())
    }
}