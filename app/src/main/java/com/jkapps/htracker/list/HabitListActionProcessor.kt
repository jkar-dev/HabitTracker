package com.jkapps.htracker.list

import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.list.HabitListAction.*
import com.jkapps.htracker.list.HabitListResult.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HabitListActionProcessor(private val repository: HabitRepository) {

    suspend fun transformActionToResult(action: HabitListAction): Flow<HabitListResult> {
        return when (action) {
            is OpenAddEditDialog -> flow { OpenDialog }
            is CloseAddEditDialog -> flow { CloseDialog }
            is EffectOnHabits -> effectOnHabits(action)
        }
    }

    private suspend fun effectOnHabits(action: EffectOnHabits): Flow<HabitListResult> {
        when (action) {
            is EffectOnHabits.GetAllHabits -> { }
            is EffectOnHabits.ChangeDoneUnits -> changeDoneUnits(action.habit, action.makeUnitDone)
            is EffectOnHabits.SaveHabit -> saveHabit(action.habit)
        }
        return repository.getAllHabits().map { NewHabitList(it) }
    }

    private suspend fun changeDoneUnits(habit: Habit, makeUnitDone: Boolean) {
        val doneUnits = if (makeUnitDone) habit.doneUnits + 1 else habit.doneUnits - 1
        val newHabit = habit.copy(doneUnits = doneUnits)
        repository.updateHabit(newHabit)
    }

    private suspend fun saveHabit(habit: Habit) {
        repository.saveHabit(habit)
    }
}