package com.thiagoperea.gitfolio.ui.screens.userdetails

import com.thiagoperea.gitfolio.data.model.RepositoryResponse
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse

sealed class UserDetailsState {

    object Loading : UserDetailsState()

    data class Success(
        val userDetails: UserDetailsResponse,
        val userRepositories: List<RepositoryResponse>
    ) : UserDetailsState()

    data class Error(val errorMessage: String) : UserDetailsState()
}