package com.keludstats.modul.dashboard.fragment.indicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keludstats.databinding.DashboardSubindikatorItemBinding
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.singletondata.IsLoggedIn


class IndicatorSubItemRecyclerAdapter(private val subindicators: Array<Subindicator>,
    private val view: IndicatorContract.View )
    : RecyclerView.Adapter<IndicatorSubItemRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: DashboardSubindikatorItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(subindicator: Subindicator) {
            binding.subindicator = subindicator
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DashboardSubindikatorItemBinding.inflate(layoutInflater)

        return MyViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return subindicators.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            bind(subindicators[position])
            binding.subindicatorBtn.setOnClickListener {
                view.redirectToTable(subindicators[position].id)
            }
            checkIfAlreadyLogin(holder)
        }
    }

    private fun checkIfAlreadyLogin(holder: MyViewHolder) {
        val visibility =
            if(IsLoggedIn.isLoggedIn)
                View.VISIBLE
            else
                View.GONE

        //hide edit and delete button if not logged in
        holder.binding.apply {
            deleteSubindicatorBtn.visibility = visibility
            editSubindicatorBtn.visibility = visibility
        }
    }
}