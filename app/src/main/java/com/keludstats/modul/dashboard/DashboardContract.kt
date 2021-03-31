package com.keludstats.modul.dashboard

interface DashboardContract {
    interface View {
        fun changePageToEtc()
        fun changePageToIndicator()
        fun changePageToHome()
    }
}