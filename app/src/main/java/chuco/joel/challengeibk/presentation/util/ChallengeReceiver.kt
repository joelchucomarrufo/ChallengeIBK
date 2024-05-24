package chuco.joel.challengeibk.presentation.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChallengeReceiver (
    val showMessage: () -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        when(intent.action) {
            "chuco.joel.challengeibk.SESSION_EXPIRED" -> {
                showMessage()
            }
        }
    }

}