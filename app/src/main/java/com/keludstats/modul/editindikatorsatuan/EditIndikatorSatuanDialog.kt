package com.keludstats.modul.editindikatorsatuan

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.databinding.InputIndikatorSatuanBinding
import com.keludstats.modul.newindikatorsatuan.NewIndikatorSatuanDialog
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog

class EditIndikatorSatuanDialog(private val indikatorSatuan: IndikatorSatuan)
    : InputIndicatorDialog(), EditIndikatorSatuanContract.View
{
    private lateinit var newIndikatorSatuanListener: NewIndikatorSatuanDialog.NewIndikatorSatuanListener
    private lateinit var binding: InputIndikatorSatuanBinding
    private val presenter = EditIndikatorSatuanPresenter(this)
    private lateinit var editIndikatorSatuanListener: EditIndikatorSatuanListener

    interface EditIndikatorSatuanListener {
        fun updateIndikatorSatuanList(indikatorSatuan: IndikatorSatuan)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            binding = InputIndikatorSatuanBinding.inflate(it.layoutInflater)
            binding.indikatorSatuan = indikatorSatuan

            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_indikator_satuan)
                    .setView(binding.root)
                    .setPositiveButton(getString(R.string.create)) { _, _ ->
                        binding.indikatorSatuan?.let {
                            presenter.updateIndicator(it)
                        }
                    }
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        editIndikatorSatuanListener = activity as EditIndikatorSatuanListener
    }

    override fun updateList(indikatorSatuan: IndikatorSatuan) {
        editIndikatorSatuanListener.updateIndikatorSatuanList(indikatorSatuan)
    }
}