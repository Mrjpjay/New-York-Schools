package com.example.nycschools.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.databinding.ItemSchoolBinding
import com.example.nycschools.model.SchoolModel

class SchoolAdapter(private val schools:List<SchoolModel>,
                    private val listener: schoolClickListener): RecyclerView.Adapter<SchoolAdapter.schoolViewHolder>() {

    inner class schoolViewHolder(val biding:ItemSchoolBinding): RecyclerView.ViewHolder(biding.root)

    interface schoolClickListener{
        fun schoolOnCLick(schoolName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): schoolViewHolder {
        val biding : ItemSchoolBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_school,
            parent,
            false
        )
        return schoolViewHolder(biding)
    }

    override fun onBindViewHolder(holder: schoolViewHolder, position: Int) {
        holder.biding.viewModel =schools[position]
        holder.biding.tvSchoolName.setOnClickListener(View.OnClickListener {
            listener.schoolOnCLick(schools.get(position).school)
        })
    }

    override fun getItemCount(): Int =
        schools.size
}