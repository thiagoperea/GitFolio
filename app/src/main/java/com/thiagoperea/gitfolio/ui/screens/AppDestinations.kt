package com.thiagoperea.gitfolio.ui.screens

object AppDestinations {

    fun userList() = "user-list"

    fun userDetails(selectedUserId: Any? = null) = "user-details/" + (selectedUserId ?: "{userId}")
}
