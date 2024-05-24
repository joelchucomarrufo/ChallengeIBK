package chuco.joel.challengeibk.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?
)