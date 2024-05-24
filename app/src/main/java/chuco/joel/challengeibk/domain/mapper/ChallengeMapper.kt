package chuco.joel.challengeibk.domain.mapper

import chuco.joel.challengeibk.data.network.response.CuentaResponse
import chuco.joel.challengeibk.data.network.response.CuentasResponse
import chuco.joel.challengeibk.data.network.response.LoginResponse
import chuco.joel.challengeibk.data.network.response.MovimientoResponse
import chuco.joel.challengeibk.data.network.response.MovimientosResponse
import chuco.joel.challengeibk.domain.model.CuentaModel
import chuco.joel.challengeibk.domain.model.CuentasModel
import chuco.joel.challengeibk.domain.model.LoginModel
import chuco.joel.challengeibk.domain.model.MovimientoModel
import chuco.joel.challengeibk.domain.model.MovimientosModel

fun LoginResponse.toDomain() = LoginModel(
    message = message,
    error = error
)

fun CuentasResponse.toDomain() = CuentasModel(
    cuentas = accounts?.map { it.toDomain() }
)

fun CuentaResponse.toDomain() = CuentaModel(
    id = id,
    nombre = accountName,
    numero = accountNumber,
    saldo = balance ?: 0.0,
    simbolo = symbol,
    simboloSaldo = "$symbol ${String.format("%.2f", balance)}"
)

fun MovimientosResponse.toDomain() = MovimientosModel(
    movimientos = movements?.map { it.toDomain() }
)

fun MovimientoResponse.toDomain() = MovimientoModel(
    fechaHora = dateTime,
    descripcion = description,
    simbolo = symbol,
    monto = amount ?: 0.0,
    simboloMonto = "$symbol ${String.format("%.2f", amount)}"
)