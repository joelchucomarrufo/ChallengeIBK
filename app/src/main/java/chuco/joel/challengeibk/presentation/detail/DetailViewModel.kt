package chuco.joel.challengeibk.presentation.detail

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chuco.joel.challengeibk.domain.model.MovimientoModel
import chuco.joel.challengeibk.domain.usecase.MovimientosUseCase
import chuco.joel.challengeibk.domain.utils.ResultType
import chuco.joel.challengeibk.presentation.detail.adapter.MovementsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: MovimientosUseCase
) : ViewModel() {

    private var movementsList = mutableListOf<MovimientoModel>()
    var error = MutableLiveData("")
    var loading = MutableLiveData(false)
    var isEmpty = MutableLiveData(true)
    var _adapterMovements: MovementsAdapter? = null

    fun load(id: Int) {
        viewModelScope.launch {
            useCase.invoke(id).collect {
                when (it) {
                    is ResultType.Loading -> {
                        loading.value = true
                    }
                    is ResultType.Success -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loading.value = false
                            movementsList = it.data.movimientos?.toMutableList() ?: arrayListOf()
                            _adapterMovements?.bindItems(movementsList)
                            isEmpty.value = movementsList.isEmpty()
                        }, 3000)
                    }
                    is ResultType.Error -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val er = it.exception
                            loading.value = false
                            error.value = er.message.toString()
                            isEmpty.value = movementsList.isEmpty()
                        }, 3000)
                    }
                }
            }
        }
    }

}