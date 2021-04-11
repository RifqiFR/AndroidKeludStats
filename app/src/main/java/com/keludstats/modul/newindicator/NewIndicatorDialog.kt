package com.keludstats.modul.newindicator

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.keludstats.R
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.modul.LoadingDialog
import com.simple.pos.shared.extension.showToast

class NewIndicatorDialog : DialogFragment(), NewIndicatorContract.View {
    private val presenter: NewIndicatorContract.Presenter = NewIndicatorPresenter(this)
    private var loadingDialog: LoadingDialog? = null

    interface CreateIndicator {
        fun addIndicator(indicator: Indikator)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_new_indicator)
                    .setView(R.layout.input_indicator_dialog)
                    .setPositiveButton(getString(R.string.create), DialogInterface.OnClickListener{ _, _ ->
                        createIndicator()
                    })
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createIndicator() {
        dialog?.findViewById<TextInputEditText>(R.id.indicatorNameEt)?.text?.let{
            presenter.createIndicator(Indikator(it.toString()))
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

    override fun updateList(indicator: Indikator) {
        if(targetFragment != null)
            (targetFragment as CreateIndicator).addIndicator(indicator)
        dismiss()
    }
}