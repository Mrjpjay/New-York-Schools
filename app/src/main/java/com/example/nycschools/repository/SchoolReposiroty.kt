package com.example.nycschools.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.nycschools.database.SchoolsDatabase
import com.example.nycschools.model.SchoolModel
import com.example.nycschools.retrofit.API
import com.example.nycschools.retrofit.SatApi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SchoolReposiroty(application: Application){

    val BASE_URL = "https://data.cityofnewyork.us/resource/"
    val SAT_BASE_URL = "https://data.cityofnewyork.us/resource/"

    var db = SchoolsDatabase.getDatabase(application).SchoolDAO()

    private val schoolApi:API = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build().create(API::class.java)

    private val SatApi:SatApi = Retrofit.Builder()
        .baseUrl(SAT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build().create(com.example.nycschools.retrofit.SatApi::class.java)

    fun getRetrofit(): API{
       return schoolApi
    }

    fun getSchoolsFromDb(): LiveData<List<SchoolModel>>{
        return db.getAllSchools()
    }

    fun getSatResult(): SatApi{
         return SatApi
    }

}