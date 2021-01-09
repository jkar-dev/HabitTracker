package com.jkapps.htracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jkapps.htracker.data.HabitRepositoryImpl
import com.jkapps.htracker.list.HabitListViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass) {
            HabitListViewModel::class.java  -> return HabitListViewModel(HabitRepositoryImpl()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}