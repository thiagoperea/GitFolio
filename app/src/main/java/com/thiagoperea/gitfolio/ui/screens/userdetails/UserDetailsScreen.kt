package com.thiagoperea.gitfolio.ui.screens.userdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UserDetailsScreen(
    userId: String,
    onNavigateUp: () -> Unit
) {

    Text("UserId: ${userId}")
}