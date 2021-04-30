package com.keludstats.modul.years

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.base.modul.BaseRecyclerAdapter
import com.keludstats.databinding.YearsItemBinding
import com.keludstats.shared.model.Year
import com.keludstats.shared.singletondata.IsLoggedIn

class YearsRecyclerAdapter(years: ArrayList<Year>, private val view: YearsContract.View)
    : BaseRecyclerAdapter<Year, YearsRecyclerAdapter.MyViewHolder>(years)
{
    class MyViewHolder(val binding: YearsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(year: Year) {
            binding.year = year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = YearsItemBinding.inflate(layoutInflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

        checkIfAlreadyLoggedIn(holder)
        holder.binding.deleteYearBtn.setOnClickListener {
            showDeleteConfirmationDialog(holder, items[position])
        }
        holder.binding.yearItemBtn.setOnClickListener {
            view.redirectToIndikatorSatuansPage(items[position].tahun)
        }
    }

    private fun checkIfAlreadyLoggedIn(holder: MyViewHolder) {
        val visibility = if(IsLoggedIn.isLoggedIn) View.VISIBLE else View.GONE

        holder.binding.deleteYearBtn.visibility = visibility
    }

    private fun showDeleteConfirmationDialog(holder: MyViewHolder, year: Year) {
        MaterialAlertDialogBuilder(holder.itemView.context)
            .setMessage(R.string.year_delete_confirmation)
            .setPositiveButton(R.string.yes) { _, _ ->
                view.deleteYear(year)
            }
            .setNegativeButton(R.string.no) { _, _ ->
            }
            .create()
            .show()
    }
}