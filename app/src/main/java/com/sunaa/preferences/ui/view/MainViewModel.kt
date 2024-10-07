package com.sunaa.preferences.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunaa.preferences.localpreferences.UserChoices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userChoice: UserChoices) : ViewModel() {

    private val _switchState = MutableStateFlow(false)
    private val _darkThemeState = MutableStateFlow(false)


    fun setUserPref(){
        _darkThemeState.value = !this._darkThemeState.value
        _switchState.value = !this._switchState.value
        this.saveUserPref()
    }


    val isDarkTheme = userChoice.getThemeState().stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = false
    )
    val switchStatePref = userChoice.getSwitchState().stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = false
    )

    private fun saveUserPref(){
        viewModelScope.launch {
            userChoice.saveUserPreferences(_darkThemeState.value,_switchState.value)
        }
    }

}