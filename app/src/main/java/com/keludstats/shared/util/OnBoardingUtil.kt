package com.keludstats.shared.util

import android.content.Context
import com.google.gson.Gson
import com.keludstats.base.util.UtilInterface
import com.keludstats.shared.model.Token

class OnBoardingUtil
    : SharedPreferencesUtil<Boolean>
{
    companion object {
        private val SHARED_PREFS_NAME = "OnBoardingSharedPrefs"
        private val SESSION_ONBOARDING = "SessionOnBoarding"
    }

    constructor() : super()
    constructor(context: Context?, SharedPrefsName: String) : super(context, SharedPrefsName)

    override fun create(context: Context?): UtilInterface {
        return OnBoardingUtil(context, SESSION_ONBOARDING)
    }

    public override fun getSessionData(): Boolean {
        val sessionDataJson = sharedPrefs.getString(SESSION_ONBOARDING, null)
        return if (sessionDataJson != null) {
            Gson().fromJson<Boolean>(sessionDataJson, Boolean::class.java)
        } else false
    }

    override fun setSessionData(sessionData: Boolean?) {
        val editor = sharedPrefs.edit()

        editor.putString(SESSION_ONBOARDING, Gson().toJson(sessionData))
        editor.apply()
    }


}