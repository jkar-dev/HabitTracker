package com.jkapps.htracker.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.list.HabitListIntent
import com.jkapps.htracker.list.HabitListState
import com.jkapps.htracker.list.HabitListViewModel
import com.jkapps.htracker.ui.*

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

@Composable
fun Toolbar(progressPercentage: Int) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = "${progressPercentage}%", color = Color.Black, fontSize = 14.sp)
            Spacer(modifier = Modifier.preferredHeight(4.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .preferredHeight(8.dp)
                    .background(
                        Brush.horizontalGradient(
                            0f to red,
                            1 / 6f to orange,
                            2 / 6f to yellow,
                            3 / 6f to green,
                            4 / 6f to blue,
                            5 / 6f to violet
                        )
                    )
            )
        }
    }
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
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
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