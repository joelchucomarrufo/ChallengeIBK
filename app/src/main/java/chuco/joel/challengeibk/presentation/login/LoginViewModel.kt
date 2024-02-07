package chuco.joel.challengeibk.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import chuco.joel.challengeibk.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: LoginUseCase) : ViewModel() {

    var username: String = ""
    var password: String = ""

    fun onLoginClicked() {
        Log.i("**** LoginViewModel", "**** Click ****")
        Log.i("**** username", username)
        Log.i("**** password", password)
        if (username == "usuario" && password == "contraseña") {
            // Iniciar sesión exitosamente
            // Por ejemplo, puedes navegar a otra actividad
        } else {
            // Mostrar mensaje de error o realizar alguna otra acción
        }
    }

}