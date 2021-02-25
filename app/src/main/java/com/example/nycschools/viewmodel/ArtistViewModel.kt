package com.example.nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nycschools.model.ArtistModel
import com.example.nycschools.repository.ArtistReposiroty
import io.reactivex.Observable

class ArtistViewModel(val reposiroty: ArtistReposiroty, application: Application): AndroidViewModel(application) {

    lateinit  var artists: Observable<ArtistModel>

      init{
          initializeArtist()
     }

    fun initializeArtist() {
//        artists = getArtistCall()
    }

    fun getArtistCall(artistName: String): Observable<ArtistModel> {
        var list = reposiroty.getRetrofit().getArtists(artistName)
        return list
    }
}
