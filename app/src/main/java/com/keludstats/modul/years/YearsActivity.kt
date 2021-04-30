package com.keludstats.modul.years

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keludstats.R
import com.keludstats.databinding.YearsActivityBinding
import com.keludstats.modul.createyear.CreateYearDialog
import com.keludstats.modul.indikatorsatuans.IndikatorSatuanActivity
import com.keludstats.shared.model.Year
import com.keludstats.shared.modul.LoadingDialog
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class YearsActivity : AppCompatActivity(), YearsContract.View, CreateYearDialog.CreateYearListener {
    private lateinit var binding: YearsActivityBinding
    private val presenter: YearsContract.Presenter = YearsPresenter(this)
    private var loadingDialog: LoadingDialog? = null
    private var subIndicatorId: Int = 0
    private var createYearDialog: CreateYearDialog? = null

    companion object {
        const val YEARS_BUNDLE_KEY = "YEARS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.years_activity)
        checkIfLoggedIn()
        presenter.retrieveAndShowYears()
        subIndicatorId = intent.getIntExtra(YEARS_BUNDLE_KEY, 0)
    }

    private fun checkIfLoggedIn() {
        if(IsLoggedIn.isLoggedIn) {
            binding.addYearButton.visibility = View.VISIBLE
            binding.addYearButton.setOnClickListener {
                showCreateYearDialog()
            }
        }else
            binding.addYearButton.visibility = View.GONE
    }

    override fun showYears(years: ArrayList<Year>) {
        binding.yearsRv.adapter = YearsRecyclerAdapter(years, this)
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

    override fun removeYearFromList(year: Year) {
        (binding.yearsRv.adapter as YearsRecyclerAdapter).removeItem(year)
    }

    override fun deleteYear(year: Year) {
        presenter.deleteYear(year)
    }

    override fun showCreateYearDialog() {
        if(createYearDialog == null) {
            createYearDialog = CreateYearDialog()
        }

        supportFragmentManager.let {
            createYearDialog?.show(it, TAG)
        }
    }

    override fun redirectToIndikatorSatuansPage(year: Int) {
        startActivity(
            Intent(this, IndikatorSatuanActivity::class.java)
                    .putExtra(IndikatorSatuanActivity.INDIKATOR_SATUAN_ID_BUNDLE_KEY, subIndicatorId)
                    .putExtra(IndikatorSatuanActivity.INDIKATOR_SATUAN_YEAR_BUNDLE_KEY, year)
        )
    }

    override fun updateYearList(year: Year) {
        presenter.retrieveAndShowYears()
    }
}