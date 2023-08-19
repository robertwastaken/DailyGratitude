package com.robert.dailygratitude.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.robert.dailygratitude.ui.EmptyScreen
import com.robert.dailygratitude.ui.detailsscreen.DetailsScreen
import com.robert.dailygratitude.ui.homescreen.HomeScreen

const val NAVIGATION_DAILY_GRATITUDE = "dailygratitude"
const val NAVIGATION_ENTRY_DETAILS = "$NAVIGATION_DAILY_GRATITUDE/{entryId}?newEntry={newEntry}"
const val NAVIGATION_SUPPORT_GROUPS = "supportgroups"

@Composable
fun DailyGratitudeNavHost(
    navController: NavHostController,
    showSnackbar: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NAVIGATION_DAILY_GRATITUDE,
    ) {
        composable(route = NAVIGATION_DAILY_GRATITUDE) {
            HomeScreen(
                onCardClick = { entryId ->
                    navController.navigate("$NAVIGATION_DAILY_GRATITUDE/$entryId")
                },
                onAddClick = {
                    navController.navigate("$NAVIGATION_DAILY_GRATITUDE/0?newEntry=true")
                },
                showSnackbar = { message ->
                    showSnackbar(message)
                }
            )
        }

        composable(
            route = NAVIGATION_ENTRY_DETAILS,
            arguments = listOf(navArgument("newEntry") {
                defaultValue = false
                type = NavType.BoolType
            })
        ) { backStackEntry ->
            DetailsScreen(
                onBack = { navController.popBackStack() },
                showSnackbar = { message -> showSnackbar(message) }
            )
        }

        composable(route = NAVIGATION_SUPPORT_GROUPS) {
            EmptyScreen()
        }
    }
}