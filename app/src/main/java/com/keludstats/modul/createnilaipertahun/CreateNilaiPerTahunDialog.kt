package com.keludstats.modul.createnilaipertahun

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.keludstats.R
import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.modul.inputindicator.InputIndicatorDialog
import java.lang.Exception

class CreateNilaiPerTahunDialog(indikatorSatuanId : Int, year: Int)
    : InputIndicatorDialog(), CreateNilaiPerTahunContract.View
{
    private val nilaiPerTahun = NilaiPerTahun(year, 0f, indikatorSatuanId)
    private lateinit var createNilaiPerTahunListener: CreateNilaiPerTahunListener
    private val presenter = CreateNilaiPerTahunPresenter(this)

    interface CreateNilaiPerTahunListener {
        fun updateNilaiPerTahunInList(data: NilaiPerTahun)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            MaterialAlertDialogBuilder(it)
                    .setTitle(R.string.create_new_value)
                    .setView(R.layout.input_nilai_per_tahun_dialog)
                    .setPositiveButton(getString(R.string.create), DialogInterface.OnClickListener{ _, _ ->
                        getValueAndCreateIt()
                    })
                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getValueAndCreateIt() {
        try {
            nilaiPerTahun.nilai = dialog?.findViewById<EditText>(R.id.nilaiPerTahunEt)?.
                                    text?.toString()?.toFloat()!!
            presenter.createNilaiPerTahun(nilaiPerTahun)
        } catch(e: Exception) {
            showFieldsCantBeEmptyError()
        }
    }

    override fun updateNilaiPerTahun(data: NilaiPerTahun) {
        createNilaiPerTahunListener.updateNilaiPerTahunInList(data)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createNilaiPerTahunListener = activity as CreateNilaiPerTahunListener
    }
}