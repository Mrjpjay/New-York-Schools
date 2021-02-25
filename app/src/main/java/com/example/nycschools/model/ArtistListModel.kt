package com.example.nycschools.model

import com.google.gson.annotations.SerializedName

class ArtistListModel(@SerializedName("artistName")val artistName:String,
                      @SerializedName("trackName")val trackName:String,
                      @SerializedName("releaseDate")val releaseDate: String,
                      @SerializedName("primaryGenreName") val primaryGenreName: String){
}