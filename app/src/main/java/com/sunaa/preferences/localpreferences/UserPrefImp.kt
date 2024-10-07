package com.sunaa.preferences.localpreferences


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPrefImp @Inject constructor(private val datastore: DataStore<Preferences>) : UserChoices {
    override fun getThemeState(): Flow<Boolean> {
        return datastore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_THEME] ?: false
        }
    }

    override fun getSwitchState(): Flow<Boolean> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[SWITCH_STATE] ?: false
        }
    }


    override suspend fun saveUserPreferences(isDarkMode: Boolean, switchState: Boolean) {
        datastore.edit { preferences ->
            preferences[USER_THEME] = isDarkMode
            preferences[SWITCH_STATE] = switchState
        }
    }
}