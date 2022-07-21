package com.vivek.sportsresult.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.vivek.sportsresult.data.models.NearestResult
import com.vivek.sportsresult.ui.theme.SportsResultTheme
import com.vivek.sportsresult.util.MainScaffold

@Composable
fun ResultScreen(navController: NavHostController, nearestResultList: List<NearestResult>?) {
    SportsResultTheme {
        MainScaffold {
            BackHandler {
                navController.popBackStack()
            }
            LazyColumn {
                if (nearestResultList != null) {
                    items(nearestResultList) { nearestResult ->
                        Text(
                            text = nearestResult.event
                        )
                    }
                }
            }
        }
    }
}