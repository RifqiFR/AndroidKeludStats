package com.keludstats.modul.newindikatorsatuan

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.databinding.InputIndikatorSatuanBinding
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog

class NewIndikatorSatuanDialog(private val subindikatorId: Int)
    : InputIndicatorDialog(), NewIndikatorSatuanContract.View
{
    private lateinit var newIndikatorSatuanListener: NewIndikatorSatuanListener
    private lateinit var binding: InputIndikatorSatuanBinding
    private val presenter: NewIndikatorSatuanContract.Presenter = NewIndikatorSatuanPresenter(this)

    interface NewIndikatorSatuanListener {
        fun updateIndikatorSatuanList(indikatorSatuan: IndikatorSatuan)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            binding = InputIndikatorSatuanBinding.inflate(it.layoutInflater)
            binding.indikatorSatuan = IndikatorSatuan("", "", subindikatorId)

            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_indikator_satuan)
                    .setView(binding.root)
                    .setPositiveButton(getString(R.string.create)) { _, _ ->
                        binding.indikatorSatuan?.let {
                            presenter.createIndikatorSatuan(it)
                        }
                    }
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        newIndikatorSatuanListener = activity as NewIndikatorSatuanListener
    }

    override fun updateList(data: IndikatorSatuan) {
        newIndikatorSatuanListener.updateIndikatorSatuanList(data)
    }
}