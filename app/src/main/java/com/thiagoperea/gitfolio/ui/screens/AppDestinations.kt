package com.thiagoperea.gitfolio.ui.screens

object AppDestinations {

    fun userList() = "user-list"

    fun userDetails(selectedUserId: String? = null) = "user-details/" + (selectedUserId ?: "{userId}")
}
