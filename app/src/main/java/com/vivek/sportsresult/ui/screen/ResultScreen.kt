package com.vivek.sportsresult.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import com.vivek.sportsresult.data.models.NearestResult

@Composable
fun ResultScreen(state: ArrayList<NearestResult>?) {
    Log.e("TAG", "ResultScreen: $state")
}