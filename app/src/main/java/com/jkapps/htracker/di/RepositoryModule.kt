package com.jkapps.htracker.di

import com.jkapps.htracker.data.HabitRepositoryImpl
import com.jkapps.htracker.data.local.HabitDao
import com.jkapps.htracker.domain.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideRepository(habitDao: HabitDao) : HabitRepository {
        return HabitRepositoryImpl(habitDao)
    }
}