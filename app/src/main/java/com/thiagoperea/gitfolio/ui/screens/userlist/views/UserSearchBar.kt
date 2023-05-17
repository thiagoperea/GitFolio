package com.thiagoperea.gitfolio.ui.screens.userlist.views

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thiagoperea.gitfolio.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSearchBar(
    modifier: Modifier = Modifier,
    onSearchRequest: (String) -> Unit = {}
) {
    val searchQuery = remember { mutableStateOf("") }

    SearchBar(
        modifier = modifier,
        query = searchQuery.value,
        onQueryChange = { newQuery ->
            searchQuery.value = newQuery
        },
        onSearch = {
            onSearchRequest(searchQuery.value)
        },
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_by_username)
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onSearchRequest(searchQuery.value)
                },
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    ) {}
}