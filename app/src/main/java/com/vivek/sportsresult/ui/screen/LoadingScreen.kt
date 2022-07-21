package com.vivek.sportsresult.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vivek.sportsresult.R
import com.vivek.sportsresult.ui.theme.getBackgroundColor

@Composable
fun LoadingFunction() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundColor()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )

        Text(text = stringResource(id = R.string.please_wait))
    }
}