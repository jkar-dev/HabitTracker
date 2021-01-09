package com.jkapps.htracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import com.jkapps.htracker.list.HabitListViewModel
import com.jkapps.htracker.ui.*
import com.jkapps.htracker.ui.list.AddHabitDialog
import com.jkapps.htracker.ui.list.ListScreen


class MainActivity : AppCompatActivity() {

    private val viewModel: HabitListViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(HabitListViewModel::class.java)
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HabitTrackerTheme {
                ListScreen(viewModel = viewModel)
            }
        }
    }
}














