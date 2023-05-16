package com.thiagoperea.gitfolio.ui.screens.userdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.thiagoperea.gitfolio.R
import com.thiagoperea.gitfolio.ui.common.LoadingView
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    userId: String,
    onNavigateUp: () -> Unit = {},
    viewModel: UserDetailsViewModel = koinViewModel()
) {
    val screenState = viewModel.screenState.value

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(userId) {
        viewModel.loadUserDetails(userId)
    }

    LaunchedEffect(screenState) {

        if (screenState is UserDetailsState.Error) {

            coroutineScope.launch {
                val actionResult = snackbarHostState.showSnackbar(
                    message = context.getString(R.string.error_message, screenState.errorMessage),
                    actionLabel = context.getString(R.string.try_again),
                    duration = SnackbarDuration.Short
                )

                if (actionResult == SnackbarResult.ActionPerformed) {
                    viewModel.loadUserDetails(userId)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateUp()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { safePaddingValues ->

        when (screenState) {
            is UserDetailsState.Loading -> LoadingView(
                modifier = Modifier.padding(safePaddingValues)
            )

            is UserDetailsState.Success -> UserDetailsScreenContent(
                modifier = Modifier.padding(safePaddingValues),
                details = screenState.userDetails,
                repositories = screenState.userRepositories
            )

            is UserDetailsState.Error -> UserDetailsScreenContent(
                modifier = Modifier.padding(safePaddingValues)
            )
        }

    }
}