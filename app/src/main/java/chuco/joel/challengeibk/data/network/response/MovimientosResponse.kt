package chuco.joel.challengeibk.data.network.response

import com.google.gson.annotations.SerializedName

data class MovimientosResponse (
    @SerializedName("movements")
    val movements: List<MovimientoResponse>?
)

data class MovimientoResponse (
    @SerializedName("dateTime")
    val dateTime: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("symbol")
    val symbol: String?
)