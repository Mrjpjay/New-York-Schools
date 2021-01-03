package com.example.nycschools.retrofit

import com.example.nycschools.model.SatResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SatApi {

    @GET("f9bf-2cp4.json")
    fun getSatResults(@Query("school_name")schoolName: String): Observable<List<SatResults>>
}