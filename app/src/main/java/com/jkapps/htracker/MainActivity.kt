package com.jkapps.htracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import com.jkapps.htracker.list.HabitListViewModel
import com.jkapps.htracker.ui.*
import com.jkapps.htracker.ui.list.AddHabitDialog
import com.jkapps.htracker.ui.list.ListScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HabitListViewModel by viewModels()

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














