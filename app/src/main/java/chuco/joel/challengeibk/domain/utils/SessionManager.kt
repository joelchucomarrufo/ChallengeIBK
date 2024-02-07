package chuco.joel.challengeibk.domain.utils

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val preferences: SharedPreferences,
) {
    private var isLoggedIn = false
    private var logoutHandler: Handler? = null
    private val logoutRunnable = Runnable { logout() }

    companion object {
        const val IS_LOGGED_IN = "is_logged_in"
    }

    fun login() {
        isLoggedIn = true
        setLoggedIn(isLoggedIn)
        startLogoutTimer()
    }

    fun logout() {
        isLoggedIn = false
        setLoggedIn(isLoggedIn)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn() : Boolean {
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }
    private fun startLogoutTimer() {
        logoutHandler?.removeCallbacks(logoutRunnable)
        logoutHandler = Handler(Looper.getMainLooper())
        logoutHandler?.postDelayed(logoutRunnable, 120000) // 2 minutos en milisegundos
    }

    private fun showSessionExpiredDialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Sesión expirada")
            .setMessage("Tu sesión ha expirado. Por favor, vuelve a iniciar sesión.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                //findNa
            }
            .setCancelable(false)
            .create()

        alertDialog.show()
    }
}