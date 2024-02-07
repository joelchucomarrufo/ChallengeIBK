package chuco.joel.challengeibk.domain.model

data class MovimientosModel (
    val movimientos: List<MovimientoModel>?
)

data class MovimientoModel (
    val fechaHora: String?,
    val descripcion: String?,
    val monto: Double?
)