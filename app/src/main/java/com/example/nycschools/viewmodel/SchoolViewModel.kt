package com.example.nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nycschools.database.SchoolsDatabase
import com.example.nycschools.model.SatResults
import com.example.nycschools.model.SchoolModel
import com.example.nycschools.repository.SchoolReposiroty
import io.reactivex.Observable
import kotlinx.coroutines.launch

class SchoolViewModel(val reposiroty: SchoolReposiroty, application: Application): AndroidViewModel(application) {

    lateinit  var schools: Observable<List<SchoolModel>>
    lateinit  var listFromDb: LiveData<List<SchoolModel>>
    lateinit var satResult: Observable<List<SatResults>>

    var db = SchoolsDatabase.getDatabase(application).SchoolDAO()

      init{
          initializeSchools()
          getSchoolsFromDb()
     }

    private fun initializeSchools() {
        schools = getSchoolsFromDao()
        listFromDb = getSchoolsFromDb()
    }

    private fun getSchoolsFromDao(): Observable<List<SchoolModel>> {
        var list = reposiroty.getRetrofit().getSchools()
        return list
    }

    fun insertToDatabase(list: List<SchoolModel>){
        viewModelScope.launch {
            db.deleteFromDatabase()
            db.insertSchool(list)
        }
    }

    fun getSchoolsFromDb(): LiveData<List<SchoolModel>> {
       return reposiroty.getSchoolsFromDb()
    }

    fun getSatValue(schoolName: String) {
      satResult = reposiroty.getSatResult().getSatResults(schoolName)
    }
}
