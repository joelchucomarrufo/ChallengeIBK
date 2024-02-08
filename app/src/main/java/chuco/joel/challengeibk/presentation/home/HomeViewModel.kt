package chuco.joel.challengeibk.presentation.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chuco.joel.challengeibk.domain.model.CuentaModel
import chuco.joel.challengeibk.domain.usecase.CuentasUseCase
import chuco.joel.challengeibk.domain.utils.ResultType
import chuco.joel.challengeibk.domain.utils.SessionManager
import chuco.joel.challengeibk.presentation.home.adapter.AccountsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: CuentasUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    var accountsList = mutableListOf<CuentaModel>()
    var error = MutableLiveData("")
    var errorUpdate = MutableLiveData("")
    var loading = MutableLiveData(false)
    var loadingPull = MutableLiveData(false)
    var isLoggedIn = MutableLiveData(false)
    var isEmpty = MutableLiveData(false)
    var _adapterAccounts: AccountsAdapter? = null

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            useCase.invoke().collect {
                when (it) {
                    is ResultType.Loading -> {
                        loading.value = true
                    }
                    is ResultType.Success -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loading.value = false
                            accountsList = it.data.cuentas?.toMutableList() ?: arrayListOf()
                            _adapterAccounts?.bindItems(accountsList)
                        }, 3000)
                    }
                    is ResultType.Error -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val er = it.exception
                            loading.value = false
                            error.value = er.message.toString()
                        }, 3000)
                    }
                }
            }
        }
    }

    fun update() {
        viewModelScope.launch {
            useCase.update().collect {
                when (it) {
                    is ResultType.Loading -> {
                        loadingPull.value = true
                    }
                    is ResultType.Success -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingPull.value = false
                            val list = mutableListOf <CuentaModel>()
                            it.data.cuentas?.forEach { cuenta ->
                                val found = accountsList.find { it.id == cuenta.id }
                                if (found == null) {
                                    accountsList.add(cuenta)
                                    list.add(cuenta)
                                }
                            }
                            _adapterAccounts?.bindNewItems(list)
                        }, 3000)
                    }
                    is ResultType.Error -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val er = it.exception
                            loadingPull.value = false
                            errorUpdate.value = er.message.toString()
                        }, 3000)
                    }
                }
            }
        }
    }

}