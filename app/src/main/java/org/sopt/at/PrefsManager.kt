package org.sopt.at

import android.content.Context
import android.content.SharedPreferences

object PrefsManager {
    private const val PREF_NAME = "user_prefs"
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveUserId(userId: Long) {
        prefs?.edit()?.putLong("userIdNum", userId)?.apply()
    }

    fun getUserId(): Long {
        return prefs?.getLong("userIdNum", -1L) ?: -1L
    }
}
