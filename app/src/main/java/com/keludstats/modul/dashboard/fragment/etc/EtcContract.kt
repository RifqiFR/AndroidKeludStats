package com.keludstats.modul.dashboard.fragment.etc

interface EtcContract {
    interface View {
        fun exitApplication()
        fun redirectToAboutApplication()
        fun redirectToNotification()
        fun redirectToPrivacy()
        fun redirectToLogin()
    }
}