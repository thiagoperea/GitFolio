package com.thiagoperea.gitfolio.data.repository

import com.thiagoperea.gitfolio.data.api.GitHubApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository(
    val api: GitHubApi,
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun listUsers() = withContext(ioDispatcher) {
        api.listUsers()
    }

    suspend fun getUserDetails(username: String) = withContext(ioDispatcher) {
        api.getUserDetails(username)
    }

    suspend fun listUserRepos(username: String) = withContext(ioDispatcher) {
        api.listRepos(username)
    }
}