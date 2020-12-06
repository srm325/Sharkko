package com.srm325.shark.ui.features.map

import androidx.lifecycle.ViewModel
import com.srm325.shark.data.Repository

class MapViewModel : ViewModel() {
    private val repository = Repository()
    fun getCurrentUser() = repository.getCurrentUser()

}