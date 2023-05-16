package com.thiagoperea.gitfolio.ui.screens.userdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.gitfolio.R
import com.thiagoperea.gitfolio.data.model.RepositoryResponse
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse
import com.thiagoperea.gitfolio.ui.common.EmptyView
import com.thiagoperea.gitfolio.ui.screens.userdetails.views.CardRepositoryList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailsScreenContent(
    modifier: Modifier = Modifier,
    details: UserDetailsResponse? = null,
    repositories: List<RepositoryResponse> = emptyList()
) {
    if (details == null) {
        return EmptyView(
            modifier = modifier,
            message = stringResource(R.string.no_data_found)
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    model = details.imageUrl,
                    contentDescription = null,
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "${details.username} • ${details.fullName}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.following_followers, details.followers, details.following)
                    )
                }
            }
        }

        items(repositories) { repositoryData ->
            CardRepositoryList(repositoryData)
        }

        item {
            Spacer(Modifier.height(8.dp))
        }
    }

}
