package com.jkapps.htracker.list

import android.util.Log
import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.list.HabitListAction.*
import com.jkapps.htracker.list.HabitListResult.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HabitListActionProcessor @Inject constructor(private val repository: HabitRepository) {
    private val allHabits : Flow<List<Habit>> = repository.allHabits

    suspend fun transformActionToResult(action: HabitListAction): Flow<HabitListResult> {
        return when (action) {
            is OpenAddEditDialog -> flowOf(OpenDialog)
            is CloseAddEditDialog -> flowOf(CloseDialog)
            is EffectOnHabits -> effectOnHabits(action)
        }
    }

    private suspend fun effectOnHabits(action: EffectOnHabits): Flow<HabitListResult> {
        when (action) {
            is EffectOnHabits.GetAllHabits -> { }
            is EffectOnHabits.TryToChangeDoneUnits -> tryToChangeDoneUnits(action.habit, action.makeUnitDone)
            is EffectOnHabits.SaveHabit -> saveHabit(action.habit)
        }
        return allHabits.map { NewHabitList(it) }
    }

    private suspend fun tryToChangeDoneUnits(habit: Habit, makeUnitDone: Boolean) {
        val doneUnits = if (makeUnitDone) habit.doneUnits + 1 else habit.doneUnits - 1
        if (doneUnits in 0..habit.timesPerDay) {
            val newHabit = habit.copy(doneUnits = doneUnits)
            repository.updateHabit(newHabit)
        }
    }

    private suspend fun saveHabit(habit: Habit) {
        repository.saveHabit(habit)
    }
}