package com.thiagoperea.gitfolio.data.repository

import com.thiagoperea.gitfolio.data.api.GitHubApi
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubRepositoryTest {

    private lateinit var repository: GitHubRepository

    @RelaxedMockK
    lateinit var apiMock: GitHubApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = GitHubRepository(apiMock)
    }

    @Test
    fun testListUsers() = runTest {
        repository.listUsers()

        coVerify(exactly = 1) { apiMock.listUsers() }
    }

    @Test
    fun testGetUserDetails() = runTest {
        repository.getUserDetails("id")

        coVerify(exactly = 1) { apiMock.getUserDetails("id") }
    }

    @Test
    fun listUserRepos() = runTest {
        repository.listUserRepos("id")

        coVerify(exactly = 1) { apiMock.listRepos("id") }
    }
}