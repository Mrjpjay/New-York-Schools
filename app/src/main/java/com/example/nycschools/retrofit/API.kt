package com.example.nycschools.retrofit

import com.example.nycschools.model.ArtistModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("search")
    fun getArtists(@Query("term") artistName: String): Observable<ArtistModel>
}