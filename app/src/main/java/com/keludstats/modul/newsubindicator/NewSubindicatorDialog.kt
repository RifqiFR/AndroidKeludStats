package com.keludstats.modul.newsubindicator

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.keludstats.R
import com.keludstats.modul.newindicator.NewIndicatorDialog
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.modul.LoadingDialog
import com.simple.pos.shared.extension.showToast

class NewSubindicatorDialog : DialogFragment(), NewSubindicatorContract.View {
    var indicatorId: Int = 0
    private val presenter: NewSubindicatorContract.Presenter = NewSubindicatorPresenter(this)
    private var loadingDialog: LoadingDialog? = null

    interface CreateSubindicatorContract {
        fun addSubindicatorToList(newSubindicator: Subindicator)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_new_subindicator)
                    .setView(R.layout.input_indicator_dialog)
                    .setPositiveButton(getString(R.string.create)) { _, _ ->
                        createSubindicator()
                    }
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createSubindicator() {
        dialog?.findViewById<TextInputEditText>(R.id.indicatorNameEt)?.text?.let {
            presenter.createSubindicator(Subindicator(it.toString(), indicatorId))
            dismiss()
        }
    }

    override fun startLoading() {
        if(loadingDialog == null)
            loadingDialog = LoadingDialog()

        fragmentManager?.let {
            loadingDialog?.show(it, null)
        }
    }

    override fun showNameCantBeEmptyError() {
        showToast(getString(R.string.name_cant_be_empty))
    }

    override fun stopLoading() {
        loadingDialog?.dismiss()
    }

    override fun updateList(subindicator: Subindicator) {
        if(targetFragment != null)
            (targetFragment as CreateSubindicatorContract).addSubindicatorToList(subindicator)
        dismiss()
    }
}