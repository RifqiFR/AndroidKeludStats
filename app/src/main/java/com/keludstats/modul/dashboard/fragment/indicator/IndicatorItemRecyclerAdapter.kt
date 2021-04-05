package com.keludstats.modul.dashboard.fragment.indicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keludstats.databinding.DashboardIndikatorItemBinding
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

class IndicatorItemRecyclerAdapter(private val indicators: Array<Indikator>,
    private val view: IndicatorContract.View)
    : RecyclerView.Adapter<IndicatorItemRecyclerAdapter.MyViewHolder>(), IndicatorContract.ItemAdapter{
    private val presenter =
        IndicatorItemPresenter(
            this
        )

    class MyViewHolder(val binding: DashboardIndikatorItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(indikator: Indikator) {
            binding.indicator = indikator
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DashboardIndikatorItemBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return indicators.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(indicators[position])

        holder.binding.indicatorNameTv.setOnClickListener {
            holder.binding.subindikatorRv.apply {
                //if recycler view visible hide it and vice versa
                if(visibility == View.GONE){
                    visibility = View.VISIBLE
                    // if it's first time showing it, load data from backend
                    if(adapter == null) {
                        presenter.showSubIndicator(indicators[position], this)
                    }
                } else
                    visibility = View.GONE
            }
        }
    }

    override fun showSubIndicator(subindicators: Array<Subindicator>, recyclerView: RecyclerView) {
        recyclerView.apply {
            adapter =
                IndicatorSubItemRecyclerAdapter(
                    subindicators, view
                )
        }
    }

    override fun getItemId(position: Int): Long {
        return indicators[position].id.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}