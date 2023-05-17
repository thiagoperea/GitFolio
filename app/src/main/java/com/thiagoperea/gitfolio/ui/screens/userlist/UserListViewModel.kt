package com.thiagoperea.gitfolio.ui.screens.userlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.gitfolio.data.model.UserResponse
import com.thiagoperea.gitfolio.data.repository.GitHubRepository
import kotlinx.coroutines.launch

class UserListViewModel(
    val repository: GitHubRepository
) : ViewModel() {

    val screenState = mutableStateOf<UserListState>(UserListState.Loading)

    fun loadUsers() {
        viewModelScope.launch {

            try {
                screenState.value = UserListState.Loading

                val users = repository.listUsers()
                screenState.value = UserListState.Success(users)
            } catch (error: Exception) {
                error.printStackTrace()
                screenState.value = UserListState.Error(error.message.orEmpty())
            }
        }
    }

    fun loadUser(username: String) {
        viewModelScope.launch {

            try {
                screenState.value = UserListState.Loading

                val response = listOf(repository.getUserDetails(username)).map { details ->
                    return@map UserResponse(
                        name = details.username,
                        imageUrl = details.imageUrl,
                        pageUrl = details.pageUrl
                    )
                }

                screenState.value = UserListState.Success(response)
            } catch (error: Exception) {
                error.printStackTrace()
                screenState.value = UserListState.Error(error.message.orEmpty())
            }
        }
    }
}