package com.robert.dailygratitude.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.robert.dailygratitude.ui.EmptyScreen
import com.robert.dailygratitude.ui.EntryDetailsScreen
import com.robert.dailygratitude.ui.HomeScreen

const val NAVIGATION_DAILY_GRATITUDE = "dailygratitude"
const val NAVIGATION_ENTRY_DETAILS = "$NAVIGATION_DAILY_GRATITUDE/{entryId}"
const val NAVIGATION_SUPPORT_GROUPS = "supportgroups"

@Composable
fun DailyGratitudeNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NAVIGATION_DAILY_GRATITUDE,
    ) {
        composable(route = NAVIGATION_DAILY_GRATITUDE) {
            HomeScreen(
                onClick = { navController.navigate("$NAVIGATION_DAILY_GRATITUDE/testId") }
            )
        }

        composable(route = NAVIGATION_ENTRY_DETAILS) { backStackEntry ->
            EntryDetailsScreen(
                entryId = backStackEntry.arguments?.getString("entryId") ?: ""
            )
        }

        composable(route = NAVIGATION_SUPPORT_GROUPS) {
            EmptyScreen()
        }
    }
}