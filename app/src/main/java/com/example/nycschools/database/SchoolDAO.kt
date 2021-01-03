package com.example.nycschools.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschools.model.SchoolModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDAO {
    @Query("SELECT * FROM nyc_school")
    fun getAllSchools(): LiveData<List<SchoolModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchool(schoolModel: List<SchoolModel>)

    @Query("DELETE FROM nyc_school")
    suspend fun deleteFromDatabase()

}