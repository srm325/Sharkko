package com.srm325.shark.uii.features.feed

interface FeedFragmentCallback {
    fun checkCurrentUser(email : String) : Boolean
    fun getCurrentUserEmail() : String?
}