package chuco.joel.challengeibk.domain.usecase

import chuco.joel.challengeibk.data.network.request.LoginRequest
import chuco.joel.challengeibk.data.repository.ChallengeRepository
import chuco.joel.challengeibk.domain.mapper.toDomain
import chuco.joel.challengeibk.domain.model.LoginModel
import chuco.joel.challengeibk.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {

    suspend fun invoke(username: String, password: String): Flow<ResultType<LoginModel>> = flow {
        emit(ResultType.Loading)
        try {
            challengeRepository.iniciarSesion(LoginRequest(username, password)).collect { data ->
                emit(ResultType.Success(data.toDomain()))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(e))
        }
    }.catch { e -> emit(ResultType.Error(e as Exception)) }

}