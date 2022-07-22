package com.vivek.sportsresult.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vivek.sportsresult.R
import com.vivek.sportsresult.data.models.NearestResult
import com.vivek.sportsresult.ui.theme.SportsResultTheme
import com.vivek.sportsresult.ui.theme.getBackgroundColor
import com.vivek.sportsresult.util.MainScaffold

@Composable
fun ResultScreen(navController: NavHostController, nearestResultList: List<NearestResult>?) {
    SportsResultTheme {
        MainScaffold { padding ->
            BackHandler {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(getBackgroundColor()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val date = nearestResultList?.first()?.day?.toString()?.substring(0, 10)
                if (date != null) {
                    Text(text = stringResource(id = R.string.result_date, date), fontSize = 30.sp)
                    Spacer(modifier = Modifier.height(40.dp))
                }

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
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
}