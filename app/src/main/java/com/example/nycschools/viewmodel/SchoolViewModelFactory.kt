package com.example.nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.database.SchoolDAO
import com.example.nycschools.repository.SchoolReposiroty

class SchoolViewModelFactory(private val schoolRepository: SchoolReposiroty,
                             private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            return SchoolViewModel(schoolRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}