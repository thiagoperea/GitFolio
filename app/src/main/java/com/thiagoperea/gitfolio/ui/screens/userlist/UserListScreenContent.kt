package com.thiagoperea.gitfolio.ui.screens.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.gitfolio.data.model.UserResponse
import com.thiagoperea.gitfolio.ui.theme.GitFolioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreenContent(
    modifier: Modifier = Modifier,
    users: List<UserResponse> = emptyList(),
    onUserSelected: (UserResponse) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(users) { userData ->

            Card(
                onClick = { onUserSelected(userData) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(64.dp),
                        model = userData.imageUrl,
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        text = userData.name
                    )

                    if (userData.pageUrl.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                print("open url: ${userData.pageUrl}")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }


}


@Preview
@Composable
fun PreviewUserListScreen() {
    GitFolioTheme {
        Surface {
            UserListScreenContent()
        }
    }
}