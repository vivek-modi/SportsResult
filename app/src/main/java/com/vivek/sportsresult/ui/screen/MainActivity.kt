package com.vivek.sportsresult.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vivek.sportsresult.R
import com.vivek.sportsresult.connection.NetworkConnection
import com.vivek.sportsresult.data.ResultFetchState
import com.vivek.sportsresult.data.models.NearestResult
import com.vivek.sportsresult.ui.screen.navigation.NavigationGraph
import com.vivek.sportsresult.ui.theme.SportsResultTheme
import com.vivek.sportsresult.ui.theme.getBackgroundColor
import com.vivek.sportsresult.util.MainScaffold
import com.vivek.sportsresult.viewmodel.MainActivityViewModel
import org.koin.androidx.compose.koinViewModel

internal lateinit var networkConnection: NetworkConnection

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnection = NetworkConnection(application)
        setContent {
            SportsResultTheme {
                SetupConnectionView()
            }
        }
    }
}

@Composable
fun SetupConnectionView() {
    val isConnected = networkConnection.observeAsState()
    if (isConnected.value == true) {
        NavigationGraph()
    } else {
        NoInternetView()
    }
}

@Composable
fun NoInternetView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundColor()),
        contentAlignment = Center,

        ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.nointernet))
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )
    }
}

@Composable
fun SetupMainActivityView(
    viewModel: MainActivityViewModel = koinViewModel(),
    navigateToResultScreen: (nearestResult: ArrayList<NearestResult>) -> Unit,
) {
    MainScaffold(content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(getBackgroundColor()),
            contentAlignment = Center
        ) {
            when (val state = viewModel.stateResultFetchState.collectAsState().value) {
                is ResultFetchState.OnSuccess -> {
                    LaunchedEffect(Unit) {
                        navigateToResultScreen(state.nearestResult)
                    }
                }
                is ResultFetchState.IsLoading -> {
                    LoadingFunction()
                }
                is ResultFetchState.OnError,
                is ResultFetchState.OnEmpty -> {
                    ActivityContent(viewModel)
                }
            }
        }
    })
}

@Composable
fun ActivityContent(viewModel: MainActivityViewModel) {
    Button(onClick = {
        viewModel.getSportResult()
    }) {
        Text(text = stringResource(id = R.string.get_result))
    }
}