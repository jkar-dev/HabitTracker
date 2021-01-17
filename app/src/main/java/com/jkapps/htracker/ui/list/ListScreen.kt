package com.jkapps.htracker.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.unit.dp
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.list.HabitListIntent
import com.jkapps.htracker.list.HabitListState
import com.jkapps.htracker.list.HabitListViewModel

@ExperimentalFoundationApi
@Composable
fun ListScreen(
    viewModel: HabitListViewModel
) {
    val state = viewModel.state.observeAsState(HabitListState.from)
    Scaffold(
        topBar = { Toolbar(state.value.progressPercentage) },
        bodyContent = {
            BodyContent(
                habits = state.value.habits,
                isDialogShowing = state.value.isDialogShowing,
                onCardClick = { },
                onLongCardClick = { viewModel.dispatchIntent(HabitListIntent.OnLongCardClick(it)) },
                onCircleClick = { viewModel.dispatchIntent(HabitListIntent.OnCircleClick(it)) },
                onLongCircleClick = { viewModel.dispatchIntent(HabitListIntent.OnLongCircleClick(it)) },
                onSaveClick = { viewModel.dispatchIntent(HabitListIntent.OnSaveHabit(it)) },
                onDialogDismiss = { viewModel.dispatchIntent(HabitListIntent.OnDialogDismiss) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.dispatchIntent(HabitListIntent.OnFabClick) }) {
                Icon(imageVector = Icons.Filled.Add)
            }
        }
    )
}


@ExperimentalFoundationApi
@Composable
fun BodyContent(
    habits: List<Habit>,
    isDialogShowing: Boolean,
    onCardClick: (Habit) -> Unit,
    onLongCardClick: (Habit) -> Unit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit,
    onSaveClick: (Habit) -> Unit,
    onDialogDismiss: () -> Unit
) {
    HabitList(
        habits = habits,
        onCardClick = onCardClick,
        onLongCardClick = onLongCardClick,
        onCircleClick = onCircleClick,
        onLongCircleClick = onLongCircleClick
    )

    if (isDialogShowing) {
        AddHabitDialog(onSaveClick = onSaveClick, onDismissRequest = onDialogDismiss)
    }
}

@ExperimentalFoundationApi
@Composable
fun HabitList(
    habits: List<Habit>,
    onCardClick: (Habit) -> Unit,
    onLongCardClick: (Habit) -> Unit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit
) {
    val orientation = AmbientConfiguration.current.orientation
    val cells = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
    LazyVerticalGrid(
        cells = GridCells.Fixed(cells),
        contentPadding = PaddingValues(6.dp)
    ) {
        items(habits) { habit ->
            HabitCard(
                habit = habit,
                onCardClick = onCardClick,
                onLongCardClick = onLongCardClick,
                onCircleClick = onCircleClick,
                onLongCircleClick = onLongCircleClick
            )
        }
    }
}