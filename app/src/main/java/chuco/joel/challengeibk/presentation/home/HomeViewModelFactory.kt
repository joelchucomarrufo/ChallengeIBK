package chuco.joel.challengeibk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chuco.joel.challengeibk.domain.usecase.CuentasUseCase
import chuco.joel.challengeibk.domain.utils.SessionManager
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val useCase: CuentasUseCase,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(useCase, sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}