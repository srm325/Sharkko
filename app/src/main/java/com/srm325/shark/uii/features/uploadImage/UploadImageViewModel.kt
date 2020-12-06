package com.srm325.shark.uii.features.uploadImage

import androidx.lifecycle.ViewModel
import com.srm325.shark.data.Repository

class UploadImageViewModel : ViewModel() {

    private val repository = Repository()

    fun getCurrentUser() = repository.getCurrentUser()

}