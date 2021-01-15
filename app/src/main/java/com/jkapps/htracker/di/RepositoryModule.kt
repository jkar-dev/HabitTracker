package com.jkapps.htracker.di

import android.content.Context
import com.jkapps.htracker.data.HabitRepositoryImpl
import com.jkapps.htracker.data.local.HabitDao
import com.jkapps.htracker.data.local.preferences.PreferenceDataStore
import com.jkapps.htracker.domain.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideRepository(habitDao: HabitDao, preferenceDataStore: PreferenceDataStore) : HabitRepository {
        return HabitRepositoryImpl(habitDao, preferenceDataStore)
    }

    @Provides
    fun providePreferenceDataStore(@ApplicationContext context: Context) : PreferenceDataStore {
        return PreferenceDataStore(context)
    }
}