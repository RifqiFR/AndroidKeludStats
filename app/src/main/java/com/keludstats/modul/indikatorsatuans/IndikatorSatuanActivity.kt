package com.keludstats.modul.indikatorsatuans

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keludstats.R
import com.keludstats.databinding.IndikatorSatuansActivityBinding
import com.keludstats.modul.createnilaipertahun.CreateNilaiPerTahunDialog
import com.keludstats.modul.editindikatorsatuan.EditIndikatorSatuanDialog
import com.keludstats.modul.newindikatorsatuan.NewIndikatorSatuanDialog
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.modul.LoadingDialog
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class IndikatorSatuanActivity : AppCompatActivity(), IndikatorSatuansContract.View,
        NewIndikatorSatuanDialog.NewIndikatorSatuanListener,
        EditIndikatorSatuanDialog.EditIndikatorSatuanListener,
        CreateNilaiPerTahunDialog.CreateNilaiPerTahunListener
{
    private lateinit var binding: IndikatorSatuansActivityBinding
    private val presenter = IndikatorSatuansPresenter(this)
    private var subindikatorId = 0
    private var year = 0
    private var loadingDialog: LoadingDialog? = null

    companion object {
        const val INDIKATOR_SATUAN_ID_BUNDLE_KEY = "INDIKATOR_SATUAN_ID_BUNDLE_KEY"
        const val INDIKATOR_SATUAN_YEAR_BUNDLE_KEY = "INDIKATOR_SATUAN_YEAR_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.indikator_satuans_activity)
        subindikatorId = intent.getIntExtra(INDIKATOR_SATUAN_ID_BUNDLE_KEY, 0)
        year = intent.getIntExtra(INDIKATOR_SATUAN_YEAR_BUNDLE_KEY, 0)
        presenter.retrieveAndShowIndikatorSatuans(subindikatorId, year)
        checkIfLoggedIn()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        binding.tambahIndikatorSatuanBtn.setOnClickListener {
            showAddIndikatorSatuanDialog()
        }
    }

    private fun checkIfLoggedIn() {
        binding.tambahIndikatorSatuanBtn.visibility =
                if(IsLoggedIn.isLoggedIn) View.VISIBLE
                else View.INVISIBLE
    }

    override fun showIndikatorSatuans(data: ArrayList<IndikatorSatuan>) {
        binding.indikatorSatuansRv.adapter = IndikatorSatuansRecyclerAdapter(data, this)
    }

    override fun showAddIndikatorSatuanDialog() {
        NewIndikatorSatuanDialog(subindikatorId).show(supportFragmentManager, TAG)
    }

    override fun deleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun) {
        presenter.deleteNilaiPerTahun(nilaiPerTahun)
    }

    override fun startLoading() {
        if(loadingDialog == null)
            loadingDialog = LoadingDialog()

        loadingDialog?.show(
                supportFragmentManager, TAG
        )
    }

    override fun stopLoading() {
        loadingDialog?.dismiss()
    }

    override fun deleteNilaiPerTahunFromList(nilaiPerTahun: NilaiPerTahun) {
        (binding.indikatorSatuansRv.adapter as IndikatorSatuansRecyclerAdapter)?.let {
            presenter.retrieveAndShowIndikatorSatuans(subindikatorId, year)
        }
    }

    override fun showUpdateIndikatorSatuanDialog(indikatorSatuan: IndikatorSatuan) {
        EditIndikatorSatuanDialog(indikatorSatuan).show(supportFragmentManager, TAG)
    }

    override fun showCreateNilaiPerTahun(indikatorSatuanId: Int) {
        CreateNilaiPerTahunDialog(indikatorSatuanId, year)
                .show(supportFragmentManager, TAG)
    }

    override fun deleteIndikatorSatuan(indikatorSatuan: IndikatorSatuan) {
        presenter.deleteIndikatorSatuan(indikatorSatuan)
    }

    override fun removeIndikatorSatuanFromList(indikatorSatuan: IndikatorSatuan) {
        (binding.indikatorSatuansRv.adapter as IndikatorSatuansRecyclerAdapter)?.let {
            it.removeItem(indikatorSatuan)
        }
    }

    override fun updateIndikatorSatuanList(indikatorSatuan: IndikatorSatuan) {
        presenter.retrieveAndShowIndikatorSatuans(subindikatorId, year)
    }

    override fun updateNilaiPerTahunInList(data: NilaiPerTahun) {
        presenter.retrieveAndShowIndikatorSatuans(subindikatorId, year)
    }
}