package com.vivek.sportsresult.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vivek.sportsresult.ui.screen.ResultScreen
import com.vivek.sportsresult.ui.screen.SetupMainActivityView
import com.vivek.sportsresult.ui.screen.SplashScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val NEAREST_RESULT_JSON = "nearestResultJson"

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Splash.route) {

        composable(route = ScreenRoute.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(ScreenRoute.Home.route) {
            SetupMainActivityView { nearestResult ->
                val nearestResultJson = Uri.encode(Json.encodeToString(nearestResult))
                navController.navigate(ScreenRoute.Result.route + "/$nearestResultJson") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }

        composable(
            ScreenRoute.Result.route + "/{$NEAREST_RESULT_JSON}",
            arguments = listOf(
                navArgument(NEAREST_RESULT_JSON) { type = NearestResultParamType() }
            )
        ) { backStackEntry ->
            ResultScreen(navController, backStackEntry.arguments?.getParcelableArrayList(NEAREST_RESULT_JSON))
        }
    }
}