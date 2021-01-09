package com.jkapps.htracker.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jkapps.htracker.base.BaseViewModel
import com.jkapps.htracker.domain.HabitRepository
import com.jkapps.htracker.list.HabitListAction.*
import com.jkapps.htracker.list.HabitListEvent.*

class HabitListViewModel(private val repository: HabitRepository) :
    BaseViewModel<HabitListState, HabitListEvent, HabitListAction, HabitListResult>() {

    override val _state: MutableLiveData<HabitListState> = MutableLiveData(initialState())
    override val state: LiveData<HabitListState> get() = _state

    override fun handleAction(action: HabitListAction) {
        Log.i("HabitListViewModel", "handle Action: action = $action")
    }

    override fun reducer(result: HabitListResult, oldState: HabitListState): HabitListState {
        return oldState.copy()
    }

    override fun transformEventToAction(event: HabitListEvent): HabitListAction {
        return when (event) {
            is OnFabClick -> OpenAddEditDialog
            is OnCardClick -> Nothing
            is OnLongCardClick -> Nothing
            is OnCircleClick -> MakeUnitDone(event.habit)
            is OnLongCircleClick -> MakeUnitUndone(event.habit)
            is OnDialogDismiss -> CloseAddEditDialog
            is OnSaveHabit -> SaveHabit(event.habit)
        }
    }

    override fun initialState(): HabitListState {
        val habits = repository.getAllHabits()
        return HabitListState(habits, false)
    }
}