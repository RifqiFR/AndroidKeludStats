package com.keludstats.modul.editsubindicator

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.keludstats.R
import com.keludstats.modul.newsubindicator.NewSubindicatorDialog
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog

class EditSubindicatorDialog(var subindicator: Subindicator)
    : InputIndicatorDialog(), EditSubindicatorContract.View {
    private val presenter: EditSubindicatorContract.Presenter = EditSubindicatorPresenter(this)

    interface EditSubindicator {
        fun updateOldSubindicator(subindicator: Subindicator)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.edit_subindicator)
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
            it.setText(subindicator.subindicatorName)
        }
    }

    private fun retrieveValueAndUpdate() {
        dialog?.findViewById<TextInputEditText>(R.id.indicatorNameEt)?.text?.let{
            val subindicator = subindicator.copy(subindicatorName = it.toString()).apply {
                id = subindicator.id
            }

            presenter.updateSubindicator(subindicator)
            dismiss()
        }
    }

    override fun updateList(data: Subindicator) {
        if(targetFragment != null)
            (targetFragment as EditSubindicator).updateOldSubindicator(subindicator)
        dismiss()
    }
}