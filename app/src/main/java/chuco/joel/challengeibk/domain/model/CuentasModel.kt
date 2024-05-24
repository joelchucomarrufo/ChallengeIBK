package chuco.joel.challengeibk.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CuentasModel (
    val cuentas: List<CuentaModel>?
)

@Parcelize
data class CuentaModel (
    val id: Int?,
    val nombre: String?,
    val numero: String?,
    val saldo: Double = 0.0,
    val simbolo: String?,
    val simboloSaldo: String?
) : Parcelable