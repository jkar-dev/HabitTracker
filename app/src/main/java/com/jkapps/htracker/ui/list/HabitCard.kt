package com.jkapps.htracker.ui.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkapps.htracker.domain.entity.Habit

@Composable
fun HabitCard(
    habit: Habit,
    onCardClick: (Habit) -> Unit,
    onLongCardClick: (Habit) -> Unit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit
) {
    WithConstraints {
        Card(
            backgroundColor = Color.White,
            elevation = 4.dp,
            modifier = Modifier
                .size(maxWidth)
                .padding(6.dp)
                .clickable(
                    onClick = { onCardClick.invoke(habit) },
                    onLongClick = { onLongCardClick.invoke(habit) })
        ) {
            CardContent(
                habit = habit,
                onCircleClick = onCircleClick,
                onLongCircleClick = onLongCircleClick
            )
        }
    }

}

@Composable
private fun CardContent(
    habit: Habit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProgressCircle(habit, onCircleClick, onLongCircleClick)
        Spacer(modifier = Modifier.preferredHeight(12.dp))

        Text(
            text = habit.title,
            color = Color.Black,
            fontSize = 16.sp
        )

        if (habit.subtitle.isNotEmpty())
            Text(
                text = habit.subtitle,
                color = Color.Gray,
                fontSize = 14.sp
            )
    }
}

@Composable
private fun ProgressCircle(
    habit: Habit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit
) {
    WithConstraints {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .padding(2.dp)
                    .size(maxWidth / 2)
                    .clickable(
                        onClick = { onCircleClick.invoke(habit) },
                        onLongClick = { onLongCircleClick.invoke(habit) },
                        indication = null
                    )
            ) {
                val anglePadding = if (habit.timesPerDay == 1) 0f else 3f
                var startAngle = -90f + anglePadding
                val fullSweepAngle = 360f / habit.timesPerDay
                val sweepAngle = fullSweepAngle - 2 * anglePadding

                for (i in 1..habit.timesPerDay) {
                    val color = if (i <= habit.doneUnits) Color.Blue else Color.LightGray
                    drawArc(
                        color = color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        size = size,
                        useCenter = false,
                        style = Stroke(5.dp.toPx())
                    )
                    startAngle += fullSweepAngle
                }
            }
            if (habit.isComplete)
                Image(
                    imageVector = Icons.Filled.Done,
                    modifier = Modifier.size(maxWidth / 3)
                )
            else
                Text(
                    text = "${habit.doneUnits}/${habit.timesPerDay}",
                    fontSize = 36.sp
                )
        }
    }
}