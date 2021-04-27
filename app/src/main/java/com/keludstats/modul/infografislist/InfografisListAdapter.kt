package com.keludstats.modul.infografislist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keludstats.R
import com.keludstats.base.modul.BaseRecyclerAdapter
import com.keludstats.databinding.InfografisItemBinding
import com.keludstats.shared.model.Infografi

class InfografisListAdapter(items: ArrayList<Infografi>)
    : BaseRecyclerAdapter<Infografi, InfografisListAdapter.MyViewHolder>(items) {

    class MyViewHolder(private val binding : InfografisItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(infografi: Infografi) {
            binding.let {
                it.infografi = infografi
                it.executePendingBindings()
                //show image
                Glide.with(it.root)
                    .load(infografi.pictureLink)
                    .placeholder(R.drawable.loading_spinner)
                    .into(it.infografiListPictureIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InfografisItemBinding.inflate(layoutInflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
}