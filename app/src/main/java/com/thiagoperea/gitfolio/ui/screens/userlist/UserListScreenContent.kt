package com.thiagoperea.gitfolio.ui.screens.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thiagoperea.gitfolio.R
import com.thiagoperea.gitfolio.data.model.UserResponse
import com.thiagoperea.gitfolio.ui.common.EmptyView
import com.thiagoperea.gitfolio.ui.screens.userlist.views.CardUserList

@Composable
fun UserListScreenContent(
    modifier: Modifier = Modifier,
    users: List<UserResponse> = emptyList(),
    onUserSelected: (UserResponse) -> Unit = {}
) {
    if (users.isEmpty()) {
        return EmptyView(
            modifier = modifier,
            message = stringResource(R.string.no_user_found)
        )
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(20.dp),
    ) {
        items(users) { userData ->

            CardUserList(
                userData = userData,
                onUserSelected = { selectedUser ->
                    onUserSelected(selectedUser)
                }
            )
        }
    }
}