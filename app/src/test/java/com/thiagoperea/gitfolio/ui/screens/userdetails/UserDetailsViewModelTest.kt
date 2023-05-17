package com.thiagoperea.gitfolio.ui.screens.userdetails

import com.thiagoperea.gitfolio.CustomCoroutineRule
import com.thiagoperea.gitfolio.data.model.RepositoryResponse
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse
import com.thiagoperea.gitfolio.data.repository.GitHubRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailsViewModelTest {

    @get:Rule
    var rule = CustomCoroutineRule()

    lateinit var viewModel: UserDetailsViewModel

    @RelaxedMockK
    lateinit var repositoryMock: GitHubRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = UserDetailsViewModel(repositoryMock)
    }

    @Test
    fun testLoadUserDetailsSuccess() = runTest {
        // Arrange
        val detailsResponse = mockk<UserDetailsResponse>()
        val reposResponse = listOf(mockk<RepositoryResponse>())

        coEvery {
            repositoryMock.getUserDetails("id")
        } returns detailsResponse

        coEvery {
            repositoryMock.listUserRepos("id")
        } returns reposResponse

        // Act
        viewModel.loadUserDetails("id")
        advanceUntilIdle()

        // Assert
        assertTrue(viewModel.screenState.value is UserDetailsState.Success)
    }

    @Test
    fun testLoadUserDetailsErrorOnDetails() = runTest {
        coEvery {
            repositoryMock.getUserDetails("id")
        } throws RuntimeException()

        // Act
        viewModel.loadUserDetails("id")
        advanceUntilIdle()

        // Assert
        assertTrue(viewModel.screenState.value is UserDetailsState.Error)
    }

    @Test
    fun testLoadUserDetailsErrorOnRepos() = runTest {
        // Arrange
        val detailsResponse = mockk<UserDetailsResponse>()

        coEvery {
            repositoryMock.getUserDetails("id")
        } returns detailsResponse

        coEvery {
            repositoryMock.listUserRepos("id")
        } throws RuntimeException()

        // Act
        viewModel.loadUserDetails("id")
        advanceUntilIdle()

        // Assert
        assertTrue(viewModel.screenState.value is UserDetailsState.Error)
    }
}