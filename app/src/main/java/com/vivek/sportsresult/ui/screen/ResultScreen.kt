package com.vivek.sportsresult.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vivek.sportsresult.data.models.NearestResult
import com.vivek.sportsresult.ui.theme.getBackgroundColor

@Composable
fun ResultScreen(nearestResultList: List<NearestResult>?) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(getBackgroundColor())
    ) {
        items(nearestResultList) { nearestResult ->
            Text(text = "$nearestResult")
        }
    }
}