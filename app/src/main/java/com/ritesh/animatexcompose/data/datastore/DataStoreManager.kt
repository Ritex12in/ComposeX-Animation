package com.ritesh.animatexcompose.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val dataStore:DataStore<Preferences>) {
    companion object{
        val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch_key")
    }

    val isFirstLaunch: Flow<Boolean> = dataStore.data.map { pref->
        pref[IS_FIRST_LAUNCH_KEY] != false
    }

    suspend fun saveIsFirstLaunch(value: Boolean){
        dataStore.edit { pref->
            pref[IS_FIRST_LAUNCH_KEY] = value
        }
    }
}