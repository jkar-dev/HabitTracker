package com.jkapps.htracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jkapps.htracker.domain.mvi.BaseAction
import com.jkapps.htracker.domain.mvi.BaseEvent
import com.jkapps.htracker.domain.mvi.BaseResult
import com.jkapps.htracker.domain.mvi.BaseState

abstract class BaseViewModel<State : BaseState, Event : BaseEvent, Action : BaseAction, Result : BaseResult> :
    ViewModel() {

    protected abstract val _state: MutableLiveData<State>
    abstract val state: LiveData<State>

    protected abstract fun initialState() : State
    protected abstract fun transformEventToAction(event: Event): Action
    protected abstract fun reducer(result: Result, oldState: State): State
    protected abstract fun handleAction(action: Action)

    fun dispatchEvent(event: Event) {
        handleAction(transformEventToAction(event))
    }

}