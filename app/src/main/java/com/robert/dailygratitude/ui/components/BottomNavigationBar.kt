package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.robert.dailygratitude.navigation.NAVIGATION_DAILY_GRATITUDE
import com.robert.dailygratitude.navigation.NAVIGATION_SUPPORT_GROUPS

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        // Support Groups
        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == NAVIGATION_SUPPORT_GROUPS } == true,
            onClick = {
                navController.navigate(NAVIGATION_SUPPORT_GROUPS) {
                    popUpTo(NAVIGATION_DAILY_GRATITUDE)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Support Groups")
            }
        )

        // Daily Gratitude
        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == NAVIGATION_DAILY_GRATITUDE } == true,
            onClick = {
                navController.navigate(NAVIGATION_DAILY_GRATITUDE) {
                    popUpTo(NAVIGATION_DAILY_GRATITUDE)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Daily Gratitude")
            }
        )
    }
}