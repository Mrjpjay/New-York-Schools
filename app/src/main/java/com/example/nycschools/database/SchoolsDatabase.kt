package com.example.nycschools.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nycschools.model.SchoolModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(SchoolModel::class), version = 1, exportSchema = false)
abstract class SchoolsDatabase : RoomDatabase() {

    abstract fun SchoolDAO() : SchoolDAO

    companion object{

        @Volatile
        private var INSTANCE: SchoolsDatabase? = null

        fun getDatabase(context: Context):SchoolsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    SchoolsDatabase::class.java,
                    "school_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}