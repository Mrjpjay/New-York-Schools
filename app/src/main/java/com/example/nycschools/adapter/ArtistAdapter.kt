package com.example.nycschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.databinding.ItemSchoolBinding
import com.example.nycschools.model.ArtistListModel
import com.example.nycschools.model.ArtistModel

class ArtistAdapter(private val artists:List<ArtistListModel>): RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(val biding:ItemSchoolBinding): RecyclerView.ViewHolder(biding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val biding : ItemSchoolBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_school,
            parent,
            false
        )
        return ArtistViewHolder(biding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.biding.viewModel =artists[position]
    }

    override fun getItemCount(): Int =
        artists.size
}