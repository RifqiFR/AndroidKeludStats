package com.keludstats.modul.detailinfografi

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.keludstats.R
import com.keludstats.databinding.DashboardInfografisDetailBinding
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.LoadingDialog
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class DetailInfografiActivity : AppCompatActivity(), DetailInfografiContract.View {
    private lateinit var binding: DashboardInfografisDetailBinding
    private var loadingDialog: LoadingDialog? = null
    private val presenter: DetailInfografiContract.Presenter = DetailInfografiPresenter(this)

    companion object {
        const val DETAIL_INFOGRAFI_BUNDLE_KEY = "DETAIL INFOGRAFI BUNDLE KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard_infografis_detail)
        (intent.extras?.get(DETAIL_INFOGRAFI_BUNDLE_KEY) as Infografi).let {
            binding.infografi = it
        } ?: finish()
        checkIfLoggedIn()
        showImage()
        initializeOnClicks()
    }

    private fun showImage() {
        Glide.with(this)
                .load(binding.infografi?.pictureLink)
                .placeholder(R.drawable.loading_spinner)
                .into(binding.detailInfografiPictureIv)
    }

    private fun initializeOnClicks() {
        binding.updateInfografiBtn.setOnClickListener {
            redirectToUpdateInfografi()
        }

        binding.deleteInfografiBtn.setOnClickListener {
            binding.infografi?.let {
                presenter.deleteInfografi(it)
            }
        }
    }

    private fun checkIfLoggedIn() {
        val visibility =
                if(IsLoggedIn.isLoggedIn)
                    View.VISIBLE
                else
                    View.GONE

        binding.deleteInfografiBtn.visibility = visibility
        binding.updateInfografiBtn.visibility = visibility
    }

    override fun startLoading() {
        if(loadingDialog == null)
            loadingDialog = LoadingDialog()

        loadingDialog?.show(supportFragmentManager, TAG)
    }

    override fun stopLoading() {
        loadingDialog?.dismiss()
    }

    override fun redirectToInfografisListAndRefreshList() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun redirectToUpdateInfografi() {
    }
}