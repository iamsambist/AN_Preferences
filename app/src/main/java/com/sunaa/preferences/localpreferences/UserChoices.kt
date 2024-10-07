package com.sunaa.preferences.localpreferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_THEME = booleanPreferencesKey("user_theme")
val SWITCH_STATE = booleanPreferencesKey("switch_state")

 interface UserChoices {
    fun getThemeState(): Flow<Boolean>
    fun getSwitchState() : Flow<Boolean>

    suspend fun saveUserPreferences(isDarkMode : Boolean, switchState : Boolean)
}