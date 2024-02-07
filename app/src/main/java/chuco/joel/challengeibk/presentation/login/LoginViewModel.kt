package chuco.joel.challengeibk.presentation.login

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chuco.joel.challengeibk.domain.usecase.LoginUseCase
import chuco.joel.challengeibk.domain.utils.ResultType
import chuco.joel.challengeibk.domain.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    var username: String = "user123&"
    var password: String = "123456"
    var message = MutableLiveData("")
    var error = MutableLiveData("")
    var loading = MutableLiveData(false)
    var isLoggedIn = MutableLiveData(false)

    init {
        isLoggedIn.value = sessionManager.isLoggedIn()
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            useCase.invoke(username, password).collect {
                when (it) {
                    is ResultType.Loading -> {
                        loading.value = true
                    }
                    is ResultType.Success -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loading.value = false
                            error.value = it.data.error
                            message.value = it.data.message

                            if(it.data.error.isNullOrEmpty()) {
                                sessionManager.login()
                            }
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

}