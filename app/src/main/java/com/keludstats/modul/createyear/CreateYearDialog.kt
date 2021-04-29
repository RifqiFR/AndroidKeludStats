package com.keludstats.modul.createyear

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.shared.model.Year
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog
import com.simple.pos.shared.extension.showToast

class CreateYearDialog : InputIndicatorDialog(), CreateYearContract.View {
    private val presenter : CreateYearContract.Presenter = CreateYearPresenter(this)
    lateinit var createYearListener: CreateYearListener

    interface CreateYearListener {
        fun updateYearList(year: Year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_new_year)
                    .setView(R.layout.input_year_dialog)
                    .setPositiveButton(getString(R.string.create), DialogInterface.OnClickListener{ _, _ ->
                        createYear()
                    })
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createYearListener = activity as CreateYearListener
    }

    private fun createYear() {
        dialog?.findViewById<EditText>(R.id.yearEt)?.text?.let {
            presenter.createYear(Year(it.toString().toInt()))
        }
    }

    override fun showCreateYearFailed() {
        showToast("Tahun gagal ditambah")
    }

    override fun updateYearList(data: Year) {
        createYearListener.updateYearList(data)
        dismiss()
    }
}