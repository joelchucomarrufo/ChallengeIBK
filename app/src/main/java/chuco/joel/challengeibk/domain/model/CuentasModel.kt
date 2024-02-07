package chuco.joel.challengeibk.domain.model

data class CuentasModel (
    val cuentas: List<CuentaModel>?
)

data class CuentaModel (
    val id: Int?,
    val nombre: String?,
    val saldo: Double?
)