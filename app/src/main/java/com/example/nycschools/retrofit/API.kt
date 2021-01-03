package com.example.nycschools.retrofit

import com.example.nycschools.model.SchoolModel
import io.reactivex.Observable
import retrofit2.http.GET

interface API {

    @GET("s3k6-pzi2.json")
    fun getSchools(): Observable<List<SchoolModel>>
}