package com.thiagoperea.gitfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.gitfolio.ui.screens.AppDestinations
import com.thiagoperea.gitfolio.ui.screens.userdetails.UserDetailsScreen
import com.thiagoperea.gitfolio.ui.screens.userlist.UserListScreen
import com.thiagoperea.gitfolio.ui.theme.GitFolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitFolioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = AppDestinations.userList(),
                    ) {

                        composable(AppDestinations.userList()) {
                            UserListScreen(
                                onUserSelected = { selectedUserId ->
                                    navigationController.navigate(AppDestinations.userDetails(selectedUserId))
                                }
                            )
                        }

                        composable(AppDestinations.userDetails()) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId").orEmpty()

                            UserDetailsScreen(
                                userId = userId,
                                onNavigateUp = {
                                    navigationController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}