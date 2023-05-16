package com.thiagoperea.gitfolio.ui.screens.userlist.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.gitfolio.data.model.UserResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardUserList(
    userData: UserResponse,
    onUserSelected: (UserResponse) -> Unit
) {
    Card(
        onClick = { onUserSelected(userData) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = userData.imageUrl,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                text = userData.name,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}