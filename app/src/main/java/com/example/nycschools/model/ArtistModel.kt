package com.example.nycschools.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(@SerializedName("results") val artist : List<ArtistListModel>) {
    
}