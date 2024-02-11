package chuco.joel.challengeibk.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chuco.joel.challengeibk.domain.usecase.MovimientosUseCase
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(
    private val useCase: MovimientosUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}