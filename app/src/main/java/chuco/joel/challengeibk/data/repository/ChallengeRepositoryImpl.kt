package chuco.joel.challengeibk.data.repository

import chuco.joel.challengeibk.data.network.ChallengeService
import chuco.joel.challengeibk.data.network.request.LoginRequest
import chuco.joel.challengeibk.data.network.response.CuentasResponse
import chuco.joel.challengeibk.data.network.response.LoginResponse
import chuco.joel.challengeibk.data.network.response.MovimientosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeService: ChallengeService
): ChallengeRepository {

    override suspend fun iniciarSesion(body: LoginRequest): Flow<LoginResponse> = flow {
        emit(challengeService.iniciarSesion(body))
    }.flowOn(Dispatchers.IO)

    override suspend fun obtenerCuentas(): Flow<CuentasResponse> = flow {
        emit(challengeService.obtenerCuentas())
    }.flowOn(Dispatchers.IO)

    override suspend fun actualizarCuentas(): Flow<CuentasResponse> = flow {
        emit(challengeService.actualizarCuentas())
    }.flowOn(Dispatchers.IO)

    override suspend fun obtenerMovimientos(id: Int): Flow<MovimientosResponse> = flow {
        emit(challengeService.obtenerMovimientos(id))
    }.flowOn(Dispatchers.IO)


}