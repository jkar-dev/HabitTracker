package com.jkapps.htracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    suspend fun getAllHabits() : Flow<List<HabitEntity>>

    @Insert
    suspend fun insertHabit(habit: HabitEntity)

    @Update
    suspend fun updateHabit(habit : HabitEntity)

}