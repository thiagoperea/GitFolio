package com.thiagoperea.gitfolio.ui.screens.userlist

import com.thiagoperea.gitfolio.data.model.UserResponse

sealed class UserListState {
    object Loading : UserListState()

    data class Success(val users: List<UserResponse>) : UserListState()

    data class Error(val errorMessage: String) : UserListState()
}