package chuco.joel.challengeibk.domain.usecase

import chuco.joel.challengeibk.data.repository.ChallengeRepository
import chuco.joel.challengeibk.domain.mapper.toDomain
import chuco.joel.challengeibk.domain.model.CuentasModel
import chuco.joel.challengeibk.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CuentasUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {

    suspend fun invoke(): Flow<ResultType<CuentasModel>> = flow {
        emit(ResultType.Loading)
        try {
            challengeRepository.obtenerCuentas().collect { data ->
                emit(ResultType.Success(data.toDomain()))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(e))
        }
    }.catch { e -> emit(ResultType.Error(e as Exception)) }

    suspend fun update(): Flow<ResultType<CuentasModel>> = flow {
        emit(ResultType.Loading)
        try {
            challengeRepository.actualizarCuentas().collect { data ->
                emit(ResultType.Success(data.toDomain()))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(e))
        }
    }.catch { e -> emit(ResultType.Error(e as Exception)) }

}