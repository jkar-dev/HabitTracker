package com.jkapps.htracker.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jkapps.htracker.base.BaseViewModel
import com.jkapps.htracker.list.HabitListAction.*
import com.jkapps.htracker.list.HabitListAction.EffectOnHabits.*
import com.jkapps.htracker.list.HabitListIntent.*
import com.jkapps.htracker.list.HabitListResult.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HabitListViewModel @ViewModelInject constructor(private val actionProcessor: HabitListActionProcessor) :
    BaseViewModel<HabitListState, HabitListIntent, HabitListAction, HabitListResult>() {

    override val _state: MutableLiveData<HabitListState> = MutableLiveData(HabitListState.from)
    override val state: LiveData<HabitListState> get() = _state

    init {
        dispatchIntent(InitialHabits)
    }

    override fun handleAction(action: HabitListAction) {
        viewModelScope.launch {
            actionProcessor.transformActionToResult(action).collect {
                _state.value = reducer(it, state.value!!)
            }
        }
    }

    override fun reducer(result: HabitListResult, oldState: HabitListState): HabitListState {
        return when (result) {
            is NewHabitList -> oldState.copy(habits = result.habits, isDialogShowing = false)
            is OpenDialog -> oldState.copy(isDialogShowing = true)
            is CloseDialog -> oldState.copy(isDialogShowing = false)
        }
    }

    override fun transformIntentToAction(intent: HabitListIntent): HabitListAction {
        return when (intent) {
            is InitialHabits -> GetAllHabits
            is OnFabClick -> OpenAddEditDialog
            //is OnCardClick -> DoNothing
            //is OnLongCardClick -> DoNothing
            is OnCircleClick -> ChangeDoneUnits(intent.habit, true)
            is OnLongCircleClick -> ChangeDoneUnits(intent.habit, false)
            is OnDialogDismiss -> CloseAddEditDialog
            is OnSaveHabit -> SaveHabit(intent.habit)
        }
    }
}