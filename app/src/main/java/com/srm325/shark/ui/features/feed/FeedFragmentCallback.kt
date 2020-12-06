package com.srm325.shark.ui.features.feed

interface FeedFragmentCallback {
    fun checkCurrentUser(email : String) : Boolean
    fun getCurrentUserEmail() : String?
}