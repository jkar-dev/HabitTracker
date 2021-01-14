package com.jkapps.htracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jkapps.htracker.domain.entity.Habit

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val title : String,
    val subtitle : String,
    val timesPerDay : Int,
    var doneUnits : Int
)