package chuco.joel.challengeibk.data.repository

import chuco.joel.challengeibk.data.network.request.LoginRequest
import chuco.joel.challengeibk.data.network.response.CuentasResponse
import chuco.joel.challengeibk.data.network.response.LoginResponse
import chuco.joel.challengeibk.data.network.response.MovimientosResponse
import kotlinx.coroutines.flow.Flow

interface ChallengeRepository  {

    suspend fun iniciarSesion(body: LoginRequest): Flow<LoginResponse>

    suspend fun obtenerCuentas(): Flow<CuentasResponse>

    suspend fun actualizarCuentas(): Flow<CuentasResponse>

    suspend fun obtenerMovimientos(id: Int): Flow<MovimientosResponse>

}