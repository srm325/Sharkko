package com.srm325.shark.ui.features.feed

import androidx.lifecycle.ViewModel
import com.srm325.shark.data.Repository

class FeedViewModel : ViewModel() {

    private val repository = Repository()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()


}