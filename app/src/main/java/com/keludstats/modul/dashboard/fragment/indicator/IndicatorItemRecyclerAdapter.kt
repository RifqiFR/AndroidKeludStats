package com.keludstats.modul.dashboard.fragment.indicator

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.keludstats.databinding.DashboardIndikatorItemBinding
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class IndicatorItemRecyclerAdapter(private val indicators: Array<Indikator>,
    private val view: IndicatorContract.View)
    : RecyclerView.Adapter<IndicatorItemRecyclerAdapter.MyViewHolder>(), IndicatorContract.ItemAdapter{

    private val presenter = IndicatorItemPresenter(this)
    private var subindicatorChangedAtId = -1

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

        holder.binding.apply {
            //hide sublist first before showing
            dropdownSubindicatorL.visibility = View.GONE
            if(subindicatorChangedAtId == position) {
                subindicatorChangedAtId = -1
                presenter.showSubIndicator(indicators[position], subindikatorRv)
                dropdownSubindicatorL.visibility = View.VISIBLE
            }

            indicatorNameTv.setOnClickListener {
                holder.binding.dropdownSubindicatorL.let {
                    //if dropdown visible hide it and vice versa
                    if(it.visibility == View.GONE){
                        it.visibility = View.VISIBLE
                        if(subindikatorRv.adapter == null)
                            presenter.showSubIndicator(indicators[position], subindikatorRv)
                    } else
                        it.visibility = View.GONE
                }
            }

            hideOrShowCreateSubindicatorButton(createNewSubindicatorBtn, indicators[position])
        }
    }

    private fun hideOrShowCreateSubindicatorButton(button: ImageButton, indikator: Indikator) {
        //hide button if not logged in
        if(IsLoggedIn.isLoggedIn){
            button.apply{
                visibility = View.VISIBLE

                setOnClickListener {
                    view.showNewSubIndicatorDialog(indikator.id)
                }
            }
        } else
            button.visibility = View.GONE
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

    fun refreshSubindicators(indicatorId: Int) {
        for(i in 0..indicators.size){
            if(indicators[i].id == indicatorId) {
                Log.d(TAG, "Changed indikator: $indicatorId")
                subindicatorChangedAtId = i
                notifyItemChanged(i)
                break
            }
        }
    }
}