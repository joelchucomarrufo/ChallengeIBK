package chuco.joel.challengeibk.data.network.response

import com.google.gson.annotations.SerializedName

data class CuentasResponse (
    @SerializedName("accounts")
    val accounts: List<CuentaResponse>?
)

data class CuentaResponse (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("accountName")
    val accountName: String?,
    @SerializedName("accountNumber")
    val accountNumber: String?,
    @SerializedName("balance")
    val balance: Double?,
    @SerializedName("symbol")
    val symbol: String?
)
