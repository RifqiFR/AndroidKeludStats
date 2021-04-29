package com.keludstats.modul.editindicator

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.keludstats.R
import com.keludstats.modul.newindicator.NewIndicatorDialog
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog

class EditIndicatorDialog(var indikator: Indikator)
    : InputIndicatorDialog(), EditIndicatorContract.View
{
    private val presenter: EditIndicatorContract.Presenter = EditIndicatorPresenter(this)

    interface EditIndicator {
        fun updateIndicatorInList(indicator: Indikator)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.edit_indicator)
                    .setView(R.layout.input_indicator_dialog)
                    .setPositiveButton(getString(R.string.update)) { _, _ ->
                        retrieveValueAndUpdate()
                    }
                    .create().apply {
                        // when showing dialog, fill form's fields with existing data
                        setOnShowListener {
                            fillField()
                        }
                    }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun fillField() {
        dialog?.findViewById<TextInputEditText>(R.id.indicatorNameEt)?.let {
            it.setText(indikator.indicatorName)
        }
    }

    private fun retrieveValueAndUpdate() {
        dialog?.findViewById<TextInputEditText>(R.id.indicatorNameEt)?.text?.let{
            val indikator = indikator.copy(indicatorName = it.toString()).apply {
                id = indikator.id
            }

            presenter.updateIndicator(indikator)
            dismiss()
        }
    }

    override fun updateOldIndicator(indicator: Indikator) {
        if(targetFragment != null)
            (targetFragment as EditIndicator).updateIndicatorInList(indicator)
        dismiss()
    }
}