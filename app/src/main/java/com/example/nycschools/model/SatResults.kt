package com.example.nycschools.model

import com.google.gson.annotations.SerializedName

data class SatResults(@SerializedName("num_of_sat_test_takers") var satTestTaker: String,

                      @SerializedName("sat_critical_reading_avg_score") var satReadingAverage: String,

                      @SerializedName("sat_math_avg_score") var satMathAvg: String,

                      @SerializedName("sat_writing_avg_score") var satWritingAvg: String){
}