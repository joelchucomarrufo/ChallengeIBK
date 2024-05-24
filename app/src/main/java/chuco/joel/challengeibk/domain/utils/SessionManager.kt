package chuco.joel.challengeibk.domain.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import java.util.Calendar
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val preferences: SharedPreferences,
    private val context: Context,
) {
    private var isLoggedIn = false
    private var logoutHandler: Handler? = null
    private val logoutRunnable = Runnable { logout() }

    companion object {
        const val IS_LOGGED_IN = "is_logged_in"
        const val CURRENT_TIME = "current_time"
    }

    fun login() {
        isLoggedIn = true
        setLoggedIn(true)
        startLogoutTimer()
        saveCurrentTime()
    }

    private fun logout() {
        isLoggedIn = false
        setLoggedIn(false)
        val intent = Intent("chuco.joel.challengeibk.SESSION_EXPIRED")
        context.sendBroadcast(intent)
    }

    private fun saveCurrentTime() {
        val currentTimeMillis = Calendar.getInstance().timeInMillis
        val editor = preferences.edit()
        editor.putLong(CURRENT_TIME, currentTimeMillis)
        editor.apply()
    }

    private fun getSavedTime(): Long {
        return preferences.getLong(CURRENT_TIME, 0)
    }

    private fun hasTwoMinutesPassed(savedTime: Long): Boolean {
        val twoMinutes = 2 * 60 * 1000
        val currentTime = Calendar.getInstance().timeInMillis
        return currentTime - savedTime >= twoMinutes
    }

    private fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn() : Boolean {
        var isLoggedIn = preferences.getBoolean(IS_LOGGED_IN, false)
        if (isLoggedIn) {
            isLoggedIn = !hasTwoMinutesPassed(getSavedTime())
        }
        return isLoggedIn
    }

    private fun startLogoutTimer() {
        logoutHandler?.removeCallbacks(logoutRunnable)
        logoutHandler = Handler(Looper.getMainLooper())
        logoutHandler?.postDelayed(logoutRunnable, 120000)
    }

}