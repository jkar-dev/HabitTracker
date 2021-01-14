package com.jkapps.htracker.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jkapps.htracker.domain.entity.Habit

@Composable
fun AddHabitDialog(onSaveClick: (Habit) -> Unit, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            shape = RoundedCornerShape(4.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Header()
                Spacer(modifier = Modifier.preferredHeight(10.dp))

                val title = remember { mutableStateOf("") }
                val description = remember { mutableStateOf("") }
                val isError = remember { mutableStateOf(false) }

                TitleInput(title = title, isError = isError)
                Spacer(modifier = Modifier.preferredHeight(2.dp))
                DescriptionInput(description = description)

                val sliderPosition = remember { mutableStateOf(1f) }
                TimesPerDay(sliderPosition = sliderPosition)
                Spacer(modifier = Modifier.preferredHeight(10.dp))
                Buttons(
                    onSaveClick = {
                        val habit = Habit(
                            title = title.value,
                            subtitle = description.value,
                            timesPerDay = sliderPosition.value.toInt()
                        )
                        onSaveClick.invoke(habit)
                    },
                    onCancelClick = onDismissRequest
                )
            }
        }
    }
}

@Composable
fun Buttons(onSaveClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = onCancelClick
        ) {
            Text(text = "CANCEL", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.preferredWidth(10.dp))
        TextButton(
            onClick = onSaveClick,
        ) {
            Text(text = "SAVE", fontSize = 16.sp)
        }
    }
}


@Composable
fun Header() {
    Text(
        text = "Добавить привычку", fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun TitleInput(title: MutableState<String>, isError: MutableState<Boolean>) {
    OutlinedTextField(
        value = title.value,
        onValueChange = { title.value = it },
        label = { Text(text = "Название") },
        isErrorValue = isError.value
    )
}

@Composable
fun DescriptionInput(description: MutableState<String>) {
    OutlinedTextField(
        value = description.value,
        onValueChange = { description.value = it },
        label = { Text(text = "Описание") }
    )
}

@Composable
fun TimesPerDay(sliderPosition: MutableState<Float>) {
    Text(
        "Количество повторений в день:",
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp)
    )
    Slider(
        value = sliderPosition.value,
        onValueChange = { sliderPosition.value = it },
        steps = 4,
        valueRange = 1f..6f,
        modifier = Modifier.height(20.dp),
        activeTickColor = MaterialTheme.colors.primary,
        activeTrackColor = Color.LightGray,
        inactiveTrackColor = Color.LightGray,
        inactiveTickColor = MaterialTheme.colors.primary,
        thumbColor = MaterialTheme.colors.primary
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
    ) {
        Text(
            text = "1",
            fontSize = 18.sp
        )
        Text(
            text = "6",
            fontSize = 18.sp
        )
    }
}
