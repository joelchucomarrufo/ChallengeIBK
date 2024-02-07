package chuco.joel.challengeibk.domain.usecase

import chuco.joel.challengeibk.data.repository.ChallengeRepository
import chuco.joel.challengeibk.domain.mapper.toDomain
import chuco.joel.challengeibk.domain.model.MovimientosModel
import chuco.joel.challengeibk.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovimientosUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {

    suspend fun invoke(id: Int): Flow<ResultType<MovimientosModel>> = flow {
        emit(ResultType.Loading)
        try {
            challengeRepository.obtenerMovimientos(id).collect { data ->
                emit(ResultType.Success(data.toDomain()))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(e))
        }
    }.catch { e -> emit(ResultType.Error(e as Exception)) }

}