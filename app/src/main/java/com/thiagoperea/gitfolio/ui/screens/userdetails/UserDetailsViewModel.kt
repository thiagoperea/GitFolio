package com.thiagoperea.gitfolio.ui.screens.userdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.gitfolio.data.model.RepositoryResponse
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse
import com.thiagoperea.gitfolio.data.repository.GitHubRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    val repository: GitHubRepository
) : ViewModel() {

    val screenState = mutableStateOf<UserDetailsState>(UserDetailsState.Loading)

    fun loadUserDetails(username: String) {
        viewModelScope.launch {

            try {
                screenState.value = UserDetailsState.Loading

                val details = repository.getUserDetails(username)
                val repositoryList = repository.listUserRepos(username)
                    .sortedBy { it.starCount }
                    .asReversed()

                screenState.value = UserDetailsState.Success(details, repositoryList)
            } catch (error: Exception) {
                screenState.value = UserDetailsState.Error(error.message.orEmpty())
            }
        }
    }
}

sealed class UserDetailsState {

    object Loading : UserDetailsState()

    data class Success(
        val userDetails: UserDetailsResponse,
        val userRepositories: List<RepositoryResponse>
    ) : UserDetailsState()

    data class Error(val errorMessage: String) : UserDetailsState()
}