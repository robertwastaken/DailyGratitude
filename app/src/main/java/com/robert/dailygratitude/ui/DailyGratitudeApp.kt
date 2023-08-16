package com.robert.dailygratitude.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.robert.dailygratitude.navigation.DailyGratitudeNavHost
import com.robert.dailygratitude.navigation.NAVIGATION_ENTRY_DETAILS
import com.robert.dailygratitude.ui.theme.DailyGratitudeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyGratitudeApp() {
    DailyGratitudeTheme {
        val navController = rememberNavController()
        var bottomBarVisibility by rememberSaveable { mutableStateOf(true) }

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        bottomBarVisibility = navBackStackEntry?.destination?.route != NAVIGATION_ENTRY_DETAILS

        Scaffold(
            bottomBar = {
                if (bottomBarVisibility) {
                    BottomNavigationBar(
                        navController = navController
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                DailyGratitudeNavHost(
                    navController = navController
                )
            }
        }
    }
}