package com.jkapps.htracker.data.local.preferences

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStore(
    context: Context
) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(dataStoreName)

    val lastSavedDate : Flow<String> = dataStore.data.map {
        it[PreferenceKeys.LAST_SAVED_DATE] ?: ""
    }

    suspend fun saveDate(date : String) {
        dataStore.edit {
            it[PreferenceKeys.LAST_SAVED_DATE] = date
        }
    }

    private object PreferenceKeys {
        val LAST_SAVED_DATE = preferencesKey<String>("last_saved_day")
    }

    companion object {
        private const val dataStoreName = "app_datastore"
    }
}