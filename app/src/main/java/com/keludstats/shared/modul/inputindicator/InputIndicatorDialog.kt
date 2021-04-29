package com.keludstats.shared.modul.inputindicator

import androidx.fragment.app.DialogFragment
import com.keludstats.R
import com.keludstats.shared.modul.LoadingDialog
import com.simple.pos.shared.extension.showToast

abstract class InputIndicatorDialog : DialogFragment(), InputIndicatorContract.View {
    private var loadingDialog: LoadingDialog? = null

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
}