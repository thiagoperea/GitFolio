package com.thiagoperea.gitfolio.ui.screens.userlist

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(
    onUserSelected: (Any) -> Unit,
    userListViewModel: UserListViewModel = koinViewModel()
){

}