package com.vivek.sportsresult.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vivek.sportsresult.ui.screen.ResultScreen
import com.vivek.sportsresult.ui.screen.SetupMainActivityView

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            SetupMainActivityView { state ->
                navController.navigate(ScreenRoute.Result.route + "/{$state}")
            }
        }

        composable(
            ScreenRoute.Result.route + "/{state}",
            arguments = listOf(
                navArgument("state") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ResultScreen(backStackEntry.arguments?.getString("state").orEmpty())
        }
    }
}