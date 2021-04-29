package com.keludstats.modul.dashboard.fragment.indicator

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.base.modul.BaseRecyclerAdapter
import com.keludstats.databinding.DashboardSubindikatorItemBinding
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.singletondata.IsLoggedIn


class IndicatorSubItemRecyclerAdapter(subIndicators: ArrayList<Subindicator>,
                                      private val view: IndicatorContract.View )
    : BaseRecyclerAdapter<Subindicator, IndicatorSubItemRecyclerAdapter.MyViewHolder>(subIndicators)
        , IndicatorContract.SubitemAdapter
{
    private val presenter = IndicatorSubItemPresenter(this)

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            bind(items[position])
            binding.subindicatorBtn.setOnClickListener {
                view.redirectToYearsPage(items[position].id)
            }

            binding.deleteSubindicatorBtn.setOnClickListener {
                showdeleteSubindicatorWarning(holder, items[position])
            }

            binding.editSubindicatorBtn.setOnClickListener {
                view.showEditSubindicatorDialog(items[position])
            }

            checkIfAlreadyLogin(holder)
        }
    }

    override fun showdeleteSubindicatorWarning(viewHolder: MyViewHolder, subindicator: Subindicator) {
        viewHolder.itemView.context.let {
            MaterialAlertDialogBuilder(it)
                .setMessage(it.getString(
                        R.string.subindicator_delete_confirmation, subindicator.subindicatorName
                ))
                .setPositiveButton(R.string.yes) { _: DialogInterface, _: Int ->
                    presenter.deleteSubindicator(subindicator)
                }
                .setNegativeButton(it.getString(R.string.no)) { _: DialogInterface, _: Int -> }
                .show()
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