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
    saldo = balance
)

fun MovimientosResponse.toDomain() = MovimientosModel(
    movimientos = movements?.map { it.toDomain() }
)

fun MovimientoResponse.toDomain() = MovimientoModel(
    fechaHora = dateTime,
    descripcion = description,
    monto = amount
)