package com.thiagoperea.gitfolio.ui.screens.userlist

import com.thiagoperea.gitfolio.CustomCoroutineRule
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse
import com.thiagoperea.gitfolio.data.model.UserResponse
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
class UserListViewModelTest {

    @get:Rule
    var rule = CustomCoroutineRule()

    lateinit var viewModel: UserListViewModel

    @RelaxedMockK
    lateinit var repositoryMock: GitHubRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = UserListViewModel(repositoryMock)
    }

    @Test
    fun testLoadUsersSuccess() = runTest {
        //Arrange
        val usersResponse = listOf(mockk<UserResponse>())

        coEvery {
            repositoryMock.listUsers()
        } returns usersResponse

        //Act
        viewModel.loadUsers()
        advanceUntilIdle()

        //Assert
        assertTrue(viewModel.screenState.value is UserListState.Success)
    }

    @Test
    fun testLoadUsersFailure() = runTest {
        //Arrange
        coEvery {
            repositoryMock.listUsers()
        } throws RuntimeException()

        //Act
        viewModel.loadUsers()
        advanceUntilIdle()

        //Assert
        assertTrue(viewModel.screenState.value is UserListState.Error)
    }

    @Test
    fun testLoadUserSuccess() = runTest {
        //Arrange
        val usersResponse = mockk<UserDetailsResponse>(relaxed = true)

        coEvery {
            repositoryMock.getUserDetails("id")
        } returns usersResponse

        //Act
        viewModel.loadUser("id")
        advanceUntilIdle()

        //Assert
        assertTrue(viewModel.screenState.value is UserListState.Success)
    }

    @Test
    fun testLoadUserFailure() = runTest {
        //Arrange
        coEvery {
            repositoryMock.getUserDetails("id")
        } throws RuntimeException()

        //Act
        viewModel.loadUser("id")
        advanceUntilIdle()

        //Assert
        assertTrue(viewModel.screenState.value is UserListState.Error)
    }
}