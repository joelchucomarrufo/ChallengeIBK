package chuco.joel.challengeibk.domain.utils

import android.content.Context
import chuco.joel.challengeibk.R

fun Double.getColor(context: Context) : Int {
    return if (this >= 0.0 ) {
        context.getColor(R.color.greenIbk)
    } else {
        context.getColor(R.color.redIbk)
    }
}