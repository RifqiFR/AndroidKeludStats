package com.keludstats.shared.modul.inputinfografi

interface InputInfografiContract {
    interface View {
        fun showEveryFieldIsMandatoryErrorMessage()
        fun startLoading()
        fun stopLoading()
        fun pickLogoFromGallery()
        fun showLogoFromGallery()
    }
}