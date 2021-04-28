package com.keludstats.modul.updateinfografi

import android.content.Intent
import android.os.Bundle
import com.keludstats.modul.detailinfografi.DetailInfografiActivity
import com.keludstats.shared.extension.showGlideImage
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografiActivity

class UpdateInfografiActivity : InputInfografiActivity(), UpdateInfografiContract.View {
    private val presenter: UpdateInfografiContract.Presenter = UpdateInfografiPresenter(this)

    companion object {
        const val UPDATE_INFOGRAFI_BUNDLE_KEY = "UPDATE_INFOGRAFI_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (intent.extras?.get(UPDATE_INFOGRAFI_BUNDLE_KEY) as Infografi).let {
            binding.inputInfografi = UpdateInfografiModel(it)
        } ?: finish()
        showOriginalImage()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        binding.saveInfografiBtn.setOnClickListener {
            presenter.updateInfografi(binding.inputInfografi as UpdateInfografiModel)
        }
        binding.uploadInfografiPhotoBtn.setOnClickListener {
            pickLogoFromGallery()
        }
    }

    override fun showOriginalImage() {
        binding.inputInfografi?.pictureLink?.let {
            showGlideImage(binding.inputInfografiIv, it)
        }
    }

    override fun redirectToDetailAndRefreshData(newInfografi: Infografi) {
        setResult(
                RESULT_OK,
                Intent().putExtra(
                        DetailInfografiActivity.DETAIL_INFOGRAFI_BUNDLE_KEY
                        , newInfografi
                )
        )

        finish()
    }
}