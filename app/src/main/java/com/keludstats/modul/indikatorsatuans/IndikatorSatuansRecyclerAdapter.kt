package com.keludstats.modul.indikatorsatuans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.base.modul.BaseRecyclerAdapter
import com.keludstats.databinding.IndikatorSatuansItemBinding
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.singletondata.IsLoggedIn

class IndikatorSatuansRecyclerAdapter(
        indikatorSatuans: ArrayList<IndikatorSatuan>, private val view: IndikatorSatuansContract.View
) : BaseRecyclerAdapter<IndikatorSatuan, IndikatorSatuansRecyclerAdapter.MyViewHolder>(indikatorSatuans)
{
    class MyViewHolder(val binding: IndikatorSatuansItemBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(indikatorSatuan: IndikatorSatuan) {
            binding.indikatorSatuan = indikatorSatuan

            binding.indikatorSatuan?.let {
                binding.nilaiIndikatorBtn.text =
                        if(it.valuePerYear.isNotEmpty())
                            it.valuePerYear[0].nilai.toString()
                        else
                            "-"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IndikatorSatuansItemBinding.inflate(layoutInflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val indikatorSatuan = items[position]

        holder.bind(indikatorSatuan)

        holder.binding.nilaiIndikatorBtn.setOnClickListener {
            if((it as Button).text.equals("-"))
                this@IndikatorSatuansRecyclerAdapter.view.showCreateNilaiPerTahun(indikatorSatuan.id)
            else
                showDeleteConfirmationDialog(holder, indikatorSatuan.valuePerYear[0])
        }

        holder.binding.updateIndikatorSatuanBtn.setOnClickListener {
            view.showUpdateIndikatorSatuanDialog(indikatorSatuan)
        }

        holder.binding.deleteIndikatorSatuanBtn.setOnClickListener {
            showDeleteIndikatorSatuanDialog(holder, indikatorSatuan)
        }

        checkIfAlreadyLoggedIn(holder)
    }

    private fun checkIfAlreadyLoggedIn(holder: MyViewHolder) {
        val visibility = if(IsLoggedIn.isLoggedIn) View.VISIBLE else View.GONE

        holder.binding.apply {
            deleteIndikatorSatuanBtn.visibility = visibility
            updateIndikatorSatuanBtn.visibility = visibility
            nilaiIndikatorBtn.isEnabled = IsLoggedIn.isLoggedIn
        }
    }

    private fun showDeleteIndikatorSatuanDialog(holder: MyViewHolder, indikatorSatuan: IndikatorSatuan) {
        MaterialAlertDialogBuilder(holder.itemView.context)
                .setMessage(holder.itemView.context.getString(
                        R.string.indikator_satuan_delete_confirmation, indikatorSatuan.name
                ))
                .setPositiveButton(R.string.yes) { _, _ ->
                    view.deleteIndikatorSatuan(indikatorSatuan)
                }
                .setNegativeButton(R.string.no) { _, _ ->
                }
                .create()
                .show()
    }

    private fun showDeleteConfirmationDialog(holder: MyViewHolder, nilaiPerTahun: NilaiPerTahun) {
        MaterialAlertDialogBuilder(holder.itemView.context)
            .setMessage(R.string.nilai_per_tahun_delete_confirmation)
            .setPositiveButton(R.string.yes) { _, _ ->
                view.deleteNilaiPerTahun(nilaiPerTahun)
            }
            .setNegativeButton(R.string.no) { _, _ ->
            }
            .create()
            .show()
    }
}