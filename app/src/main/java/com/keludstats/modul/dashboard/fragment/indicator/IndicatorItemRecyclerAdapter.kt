package com.keludstats.modul.dashboard.fragment.indicator

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.base.modul.BaseRecyclerAdapter
import com.keludstats.databinding.DashboardIndikatorItemBinding
import com.keludstats.modul.editindicator.EditIndicatorDialog
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class IndicatorItemRecyclerAdapter(indicators: ArrayList<Indikator>,
    private val view: IndicatorContract.View)
    : BaseRecyclerAdapter<Indikator, IndicatorItemRecyclerAdapter.MyViewHolder>(indicators)
        , IndicatorContract.ItemAdapter
{
    private val presenter = IndicatorItemPresenter(this)
    private var subindicatorChangedAtId = -1
    private var indicatorChangedAtIndex = -1

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

        holder.binding.apply {
            //hide sublist first before showing
            dropdownSubindicatorL.visibility = View.GONE
            if(subindicatorChangedAtId == position) {
                subindicatorChangedAtId = -1
                presenter.showSubIndicator(items[position], subindikatorRv)
                dropdownSubindicatorL.visibility = View.VISIBLE
            }

            indicatorNameTv.setOnClickListener {
                holder.binding.dropdownSubindicatorL.let {
                    //if dropdown visible hide it and vice versa
                    if(it.visibility == View.GONE){
                        it.visibility = View.VISIBLE
                        if(subindikatorRv.adapter == null)
                            presenter.showSubIndicator(items[position], subindikatorRv)
                    } else
                        it.visibility = View.GONE
                }
            }

            deleteIndicatorBtn.setOnClickListener {
                showdeleteIndicatorWarning(holder, items[position])
            }

            editIndicatorBtn.setOnClickListener {
                view.showEditIndicatorDialog(items[position])
                indicatorChangedAtIndex = position
            }

            hideOrShowCreateSubindicatorButton(createNewSubindicatorBtn, items[position])
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

    override fun showSubIndicator(subindicators: ArrayList<Subindicator>, recyclerView: RecyclerView) {
        recyclerView.apply {
            adapter =
                IndicatorSubItemRecyclerAdapter(
                    subindicators, view
                )
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun showdeleteIndicatorWarning(viewHolder: MyViewHolder, indikator: Indikator) {
        viewHolder.itemView.context.let {
            MaterialAlertDialogBuilder(it)
                    .setMessage(it.getString(
                            R.string.indicator_delete_confirmation, indikator.indicatorName
                    ))
                    .setPositiveButton(R.string.yes) { _: DialogInterface, _: Int ->
                        presenter.deleteIndicator(indikator)
                    }
                    .setNegativeButton(it.getString(R.string.no)) { _: DialogInterface, _: Int -> }
                    .show()
        }
    }

    fun refreshSubindicators(indicatorId: Int) {
        for(i in 0..items.size){
            if(items[i].id == indicatorId) {
                Log.d(TAG, "Changed indikator: $indicatorId")
                subindicatorChangedAtId = i
                notifyItemChanged(i)
                break
            }
        }
    }

    fun refreshIndicator(indikator: Indikator) {
        items[indicatorChangedAtIndex] = indikator
        notifyItemChanged(indicatorChangedAtIndex)
        indicatorChangedAtIndex = -1
    }
}