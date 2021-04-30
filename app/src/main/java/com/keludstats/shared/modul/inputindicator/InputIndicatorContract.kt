package com.keludstats.shared.modul.inputindicator

interface InputIndicatorContract {
    interface View {
        fun stopLoading()
        fun startLoading()
        fun showFieldsCantBeEmptyError()
    }
}