package chuco.joel.challengeibk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chuco.joel.challengeibk.domain.usecase.CuentasUseCase
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val useCase: CuentasUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}