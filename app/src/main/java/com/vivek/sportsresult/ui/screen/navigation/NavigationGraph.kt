package com.vivek.sportsresult.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vivek.sportsresult.ui.screen.ResultScreen
import com.vivek.sportsresult.ui.screen.SetupMainActivityView

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            SetupMainActivityView{
                navController.popBackStack()
                navController.navigate(ScreenRoute.Result.route)
            }
        }

        composable(ScreenRoute.Result.route) {
            ResultScreen()
        }
    }
}