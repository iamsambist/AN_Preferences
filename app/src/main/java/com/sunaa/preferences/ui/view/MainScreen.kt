package com.sunaa.preferences.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunaa.preferences.ui.theme.PreferencesTheme

@Composable
fun ScreenMain(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    // Collecting initial values from Preferences
    val isDark by mainViewModel.isDarkTheme.collectAsState()
    val checked by mainViewModel.switchStatePref.collectAsState()
    PreferencesTheme(isDark) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Switch(
                checked = checked,
                onCheckedChange = {
                    mainViewModel.setUserPref()
                }
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenMainPreview() {
    ScreenMain()
}