package com.example.nycschools.repository

import android.app.Application
import com.example.nycschools.retrofit.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArtistReposiroty(application: Application){

    val BASE_URL = "https://itunes.apple.com/"

    fun getRetrofit(): API{
        val schoolApi:API = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build().create(API::class.java)
       return schoolApi
    }

}