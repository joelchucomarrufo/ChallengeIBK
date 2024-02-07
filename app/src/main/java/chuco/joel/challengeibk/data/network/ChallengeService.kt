package chuco.joel.challengeibk.data.network

import chuco.joel.challengeibk.data.network.request.LoginRequest
import chuco.joel.challengeibk.data.network.response.CuentasResponse
import chuco.joel.challengeibk.data.network.response.LoginResponse
import chuco.joel.challengeibk.data.network.response.MovimientosResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengeService {

    companion object {
        const val BASE_URL = "https://challenge-ibk-app-sptq9.ondigitalocean.app/"
    }

    @POST("iniciarSesion")
    suspend fun  iniciarSesion(
        @Body body: LoginRequest
    ): LoginResponse

    @GET("obtenerCuentas")
    suspend fun obtenerCuentas(): CuentasResponse

    @GET("actualizarCuentas")
    suspend fun actualizarCuentas(): CuentasResponse

    @GET("obtenerMovimientos/{id}")
    suspend fun obtenerMovimientos(@Path("id") id: Int): MovimientosResponse
}