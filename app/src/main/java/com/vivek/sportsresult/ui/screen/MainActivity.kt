package com.vivek.sportsresult.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vivek.sportsresult.R
import com.vivek.sportsresult.connection.NetworkConnection
import com.vivek.sportsresult.data.ResultFetchState
import com.vivek.sportsresult.ui.screen.navigation.NavigationGraph
import com.vivek.sportsresult.ui.theme.SportsResultTheme
import com.vivek.sportsresult.ui.theme.getBackgroundColor
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupMainActivityView(
    viewModel: MainActivityViewModel = koinViewModel(),
    navigateToNext: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            backgroundColor = getBackgroundColor(),
            elevation = 0.dp
        )
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getBackgroundColor())
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.getSportResult()
            }) {
                Text(text = stringResource(id = R.string.get_result))
            }
        }
    })
    when (val state = viewModel.stateResultFetchState.collectAsState().value) {
        is ResultFetchState.OnSuccess -> {}
        is ResultFetchState.IsLoading -> {
            navigateToNext()
        }
        is ResultFetchState.OnError -> {}
        is ResultFetchState.OnEmpty -> {}
    }
}