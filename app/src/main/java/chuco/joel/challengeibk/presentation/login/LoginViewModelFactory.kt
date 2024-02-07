package chuco.joel.challengeibk.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chuco.joel.challengeibk.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val useCase: LoginUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}