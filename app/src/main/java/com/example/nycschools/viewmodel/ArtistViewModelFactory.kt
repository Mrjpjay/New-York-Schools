package com.example.nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.repository.ArtistReposiroty

class ArtistViewModelFactory(private val artistRepository: ArtistReposiroty,
                             private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            return ArtistViewModel(artistRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}