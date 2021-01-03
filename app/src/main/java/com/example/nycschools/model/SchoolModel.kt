package com.example.nycschools.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nyc_school")
data class SchoolModel(@SerializedName("school_name") @PrimaryKey @ColumnInfo(name = "school") val school : String) {

}