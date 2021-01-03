package com.example.nycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.R
import com.example.nycschools.adapter.SchoolAdapter
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.model.SatResults
import com.example.nycschools.model.SchoolModel
import com.example.nycschools.repository.SchoolReposiroty
import com.example.nycschools.viewmodel.SchoolViewModel
import com.example.nycschools.viewmodel.SchoolViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), SchoolAdapter.schoolClickListener{

    var TAG = MainActivity::class.simpleName
    lateinit var biding: ActivityMainBinding
    lateinit var viewModel: SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        biding.setLifecycleOwner(this )

        val application = requireNotNull(this).application

        var schoolReposiroty = SchoolReposiroty(application)
        val viewModelFactory = SchoolViewModelFactory(schoolReposiroty, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SchoolViewModel::class.java)

        viewModel.schools.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse)

        biding.viewModel = viewModel
        biding.executePendingBindings()

    }

    fun handleResponse(list: List<SchoolModel>){

        viewModel.insertToDatabase(list)
        viewModel.listFromDb.observe(this, { list: List<SchoolModel> ->
            var adapter: SchoolAdapter
            adapter = SchoolAdapter(list,this)

            var recycler = biding.mRecycler
            recycler.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adapter
        })
    }

    override fun schoolOnCLick(schoolName: String) {
        viewModel.getSatValue(schoolName.toUpperCase())
        viewModel.satResult.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleStatResponse)
    }

    fun handleStatResponse(satResults: List<SatResults>){
        if(satResults.size > 0){
            Toast.makeText(this,"Math: "+satResults.get(0).satMathAvg+" Average" +
                    satResults.get(0).satReadingAverage+" Test Taker" +
                    satResults.get(0).satTestTaker+" Writing Avg" +
                    satResults.get(0).satWritingAvg,Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this,"No SAT values",Toast.LENGTH_SHORT).show()
        }
    }
}