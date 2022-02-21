package com.example.transportschedule.android

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.transportschedule.android.schedule.ScheduleScreen
import com.example.transportschedule.android.search.SearchScreen

@Composable
fun AirScheduleApp() {
    val navController = rememberNavController()
    val showBackButton = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Schedule") },
                navigationIcon = {
                    if (!showBackButton.value) {
                        return@TopAppBar
                    }
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        NavHost(navController, startDestination = Screen.Main.route) {
            composable(route = Screen.Main.route) {
                showBackButton.value = false
                SearchScreen(
                    showRoutes = { from: String, to: String ->
                        navController.navigate(Screen.Schedule.createRoute(from, to))
                    }
                )
            }
            composable(route = Screen.Schedule.route) { backStackEntry ->
                val fromCode = backStackEntry.arguments?.getString("fromCode")
                val toCode = backStackEntry.arguments?.getString("toCode")
                requireNotNull(fromCode) { "from station code should be not null" }
                requireNotNull(toCode) { "to station code should be not null" }
                showBackButton.value = true
                ScheduleScreen(fromCode = fromCode, toCode = toCode)
            }
        }
    }
}