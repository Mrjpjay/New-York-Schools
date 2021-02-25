package com.example.nycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.R
import com.example.nycschools.adapter.ArtistAdapter
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.model.ArtistModel
import com.example.nycschools.repository.ArtistReposiroty
import com.example.nycschools.viewmodel.ArtistViewModel
import com.example.nycschools.viewmodel.ArtistViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(){

    var TAG = MainActivity::class.simpleName
    lateinit var biding: ActivityMainBinding
    lateinit var viewModel: ArtistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        biding.setLifecycleOwner(this)

        val application = requireNotNull(this).application

        var schoolReposiroty = ArtistReposiroty(application)
        val viewModelFactory = ArtistViewModelFactory(schoolReposiroty, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArtistViewModel::class.java)

        biding.button.setOnClickListener(View.OnClickListener {
            viewModel.getArtistCall(biding.etArtist.getText().toString()).observeOn(AndroidSchedulers.mainThread()
            ).subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::onError)
        })

        biding.viewModel = viewModel
        biding.executePendingBindings()

    }

    fun onError(throwable: Throwable){
        Log.i("thisList",throwable.message.toString())
    }
    fun handleResponse(list: ArtistModel){

        var adapter: ArtistAdapter
        adapter = ArtistAdapter(list.artist)

        var recycler = biding.mRecycler
        recycler.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
    }
}