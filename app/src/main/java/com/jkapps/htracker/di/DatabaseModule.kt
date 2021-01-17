package com.jkapps.htracker.di

import android.content.Context
import androidx.room.Room
import com.jkapps.htracker.data.local.room.AppDatabase
import com.jkapps.htracker.data.local.room.HabitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "habit_app.db").build()
    }

    @Provides
    fun provideHabitDao(database: AppDatabase) : HabitDao {
        return database.habitDao()
    }
}