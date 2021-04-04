package com.keludstats.modul

import android.app.Application
import com.keludstats.base.util.UtilProvider
import com.keludstats.shared.util.TokenUtil
import com.keludstats.shared.util.UserUtil

class KeludStatsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        UtilProvider.initialize(this,
                TokenUtil::class.java,
                UserUtil::class.java
        )
    }
}