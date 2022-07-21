package com.vivek.sportsresult.ui.screen.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.vivek.sportsresult.ui.screen.ResultScreen
import com.vivek.sportsresult.ui.screen.SetupMainActivityView

private const val NEAREST_RESULT_JSON = "nearestResultJson"

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            SetupMainActivityView { nearestResult ->
                val nearestResultJson = Uri.encode(Gson().toJson(nearestResult))
                navController.navigate(ScreenRoute.Result.route + "/$nearestResultJson")
            }
        }

        composable(
            ScreenRoute.Result.route + "/{$NEAREST_RESULT_JSON}",
            arguments = listOf(
                navArgument(NEAREST_RESULT_JSON) { type = NearestResultParamType() }
            )
        ) { backStackEntry ->
            ResultScreen(backStackEntry.arguments?.getParcelableArrayList(NEAREST_RESULT_JSON))
        }
    }
}