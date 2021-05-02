package com.keludstats.modul.detailinfografi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.keludstats.R
import com.keludstats.base.modul.BaseActivityWithActionBar
import com.keludstats.databinding.DashboardInfografisDetailBinding
import com.keludstats.modul.updateinfografi.UpdateInfografiActivity
import com.keludstats.shared.extension.showGlideImage
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.LoadingDialog
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class DetailInfografiActivity : BaseActivityWithActionBar(), DetailInfografiContract.View {
    private lateinit var binding: DashboardInfografisDetailBinding
    private var loadingDialog: LoadingDialog? = null
    private val presenter: DetailInfografiContract.Presenter = DetailInfografiPresenter(this)

    companion object {
        const val DETAIL_INFOGRAFI_BUNDLE_KEY = "DETAIL INFOGRAFI BUNDLE KEY"
        private const val UPDATE_INFOGRAFI_REQ_CODE = 200
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            UPDATE_INFOGRAFI_REQ_CODE -> {
                if(resultCode == RESULT_OK) {
                    setResult(RESULT_OK) //ask to refresh list after finishing this activity
                    data?.extras?.get(DETAIL_INFOGRAFI_BUNDLE_KEY)?.let {
                        binding.infografi = it as Infografi
                        Log.d(TAG, "Updated Infografi : ${it.id}")
                        binding.executePendingBindings()
                    }
                    showImage()
                }
            }
        }
    }

    private fun showImage() {
        Log.d(TAG, "Load Image : ${binding.infografi?.pictureLink}")
        binding.infografi?.pictureLink?.let {
            showGlideImage(binding.detailInfografiPictureIv, it)
        }
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
        Intent(this, UpdateInfografiActivity::class.java).apply {
            putExtra(UpdateInfografiActivity.UPDATE_INFOGRAFI_BUNDLE_KEY, binding.infografi)
        }.also {
            startActivityForResult(it, UPDATE_INFOGRAFI_REQ_CODE)
        }
    }
}