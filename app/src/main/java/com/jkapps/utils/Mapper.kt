package com.jkapps.utils

import com.jkapps.htracker.data.local.HabitEntity
import com.jkapps.htracker.domain.entity.Habit

fun Habit.toRoomEntity() : HabitEntity {
    return HabitEntity(id, title, subtitle, timesPerDay, doneUnits)
}

fun HabitEntity.toDomain() : Habit {
    return Habit(id, title, subtitle, timesPerDay, doneUnits)
}