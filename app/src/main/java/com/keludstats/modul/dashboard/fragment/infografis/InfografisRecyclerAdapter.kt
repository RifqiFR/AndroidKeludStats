package com.keludstats.modul.dashboard.fragment.infografis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keludstats.R
import com.keludstats.databinding.DashboardInfografisItemBinding
import com.keludstats.shared.model.Infografi

class InfografisRecyclerAdapter(private val infografis: Array<Infografi>)
    : RecyclerView.Adapter<InfografisRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: DashboardInfografisItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun showImage(imageUrl: String) {
            Glide.with(binding.root)
                .load(imageUrl)
                .placeholder(R.drawable.loading_spinner)
                .into(binding.infografisPictureIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DashboardInfografisItemBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return infografis.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.showImage(infografis[position].pictureLink)
    }
}