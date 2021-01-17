package com.jkapps.htracker.data

import com.jkapps.htracker.data.local.HabitDao
import com.jkapps.htracker.data.local.preferences.PreferenceDataStore
import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.utils.DateHelper
import com.jkapps.utils.toDomain
import com.jkapps.utils.toRoomEntity
import kotlinx.coroutines.flow.*
import timber.log.Timber

class HabitRepositoryImpl(private val habitDao: HabitDao, private val preferences : PreferenceDataStore) : HabitRepository {

    private var isNewDay = preferences.lastSavedDate.map { it != DateHelper.currentDay }

    override val allHabits: Flow<List<Habit>> =
        habitDao.getAllHabits().combine(isNewDay) { habits, isNewDay ->
            if (isNewDay) {
                Timber.i("isNewDay = true")
                preferences.saveDate(DateHelper.currentDay)
                clearUnitsOfAllHabits()
            }
            habits.map { it.toDomain() }
        }

    override suspend fun saveHabit(habit: Habit) {
        habitDao.insertHabit(habit.toRoomEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toRoomEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.toRoomEntity())
    }

    override suspend fun clearUnitsOfAllHabits() {
        habitDao.resetUnitsOfAllHabits()
    }
}