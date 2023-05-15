package com.thiagoperea.gitfolio.data.repository

import com.thiagoperea.gitfolio.data.api.GitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository(val api: GitHubApi) {

    suspend fun listUsers() = withContext(Dispatchers.IO) {
        api.listUsers()
    }

    suspend fun getUserDetails(username: String) = withContext(Dispatchers.IO) {
        api.getUserDetails(username)
    }

    suspend fun listUserRepos(username: String) = withContext(Dispatchers.IO) {
        api.listRepos(username)
    }
}