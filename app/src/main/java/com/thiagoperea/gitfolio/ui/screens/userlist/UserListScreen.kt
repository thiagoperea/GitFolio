package com.thiagoperea.gitfolio.ui.screens.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thiagoperea.gitfolio.R
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    onUserSelected: (Any) -> Unit,
    viewModel: UserListViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User List Screen") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { safePaddingValue ->

        val screenState = viewModel.screenState.value

        LaunchedEffect(Unit) {
            viewModel.loadUsers()
        }

        LaunchedEffect(screenState) {

            if (screenState is UserListState.Error) {
                coroutineScope.launch {
                    val actionResult = snackbarHostState.showSnackbar(
                        message = "Error: ${screenState.errorMessage}",
                        actionLabel = "Try again",
                        duration = SnackbarDuration.Short
                    )

                    if (actionResult == SnackbarResult.ActionPerformed) {
                        viewModel.loadUsers()
                    }
                }
            }
        }


        when (screenState) {
            is UserListState.Loading -> LoadingView(Modifier.padding(safePaddingValue))
            is UserListState.Success -> UserListScreenContent(
                modifier = Modifier.padding(safePaddingValue),
                users = screenState.users,
                onUserSelected = { userSelected ->
                    onUserSelected(userSelected)
                }
            )

            is UserListState.Error -> UserListScreenContent()
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.loading))
    }
}