package com.jkapps.htracker.ui.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkapps.htracker.domain.entity.Habit
import com.jkapps.htracker.ui.doneUnit
import com.jkapps.htracker.ui.notDoneUnit
import com.jkapps.htracker.ui.onSurfaceVariant

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
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 2.dp,
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
        modifier = Modifier.fillMaxSize().padding(top = 12.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProgressCircle(habit, onCircleClick, onLongCircleClick)
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = habit.title,
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )

            if (habit.subtitle.isNotBlank())
                Text(
                    text = habit.subtitle,
                    color = MaterialTheme.colors.onSurfaceVariant,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
        }

    }
}

@Composable
private fun ProgressCircle(
    habit: Habit,
    onCircleClick: (Habit) -> Unit,
    onLongCircleClick: (Habit) -> Unit
) {
    val colorDone = MaterialTheme.colors.doneUnit
    val colorNotDone = MaterialTheme.colors.notDoneUnit

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
                    val color = if (i <= habit.doneUnits) colorDone else colorNotDone
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
            val iconSize = maxWidth / 3
            if (habit.isComplete)
                Icon(
                    imageVector = Icons.Filled.Done.copy(
                        defaultHeight = iconSize,
                        defaultWidth = iconSize
                    )
                )
            else
                Text(
                    text = "${habit.doneUnits}/${habit.timesPerDay}",
                    fontSize = 36.sp
                )
        }
    }
}