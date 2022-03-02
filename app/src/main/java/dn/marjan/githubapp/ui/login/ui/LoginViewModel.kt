package dn.marjan.githubapp.ui.login.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.entity.UserInfo
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

/**
MutableLiveData should never be exposed outside the class, as the data flow is always from VM -> View which is beauty of MVVM pattern
and we should encapsulate access to MutableLiveData.
 **/

class LoginViewModel @Inject constructor(
    val repository: LoginRepository,
    val dispatcher: CoroutineDispatcherProvider
) : BaseViewModel() {

    private val _loginResponse = MutableLiveData<Resource<UserInfo>>()

    val loginResponse: LiveData<Resource<UserInfo>>
        get() = _loginResponse


    fun validateLoginReq(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _loginResponse.postValue(ErrorResource(error = Error.EmptyInputError))
        } else {
            doLogin(username = username, password = password)
        }
    }


    fun doLogin(username: String, password: String) {
        viewModelScope.launch(dispatcher.IO()) {
            showLoading()
            try {
                val res = SuccessResource(data = repository.loginReq(username, password))
                repository.saveUserData(res.data as UserInfo)
                _loginResponse.postValue(res)

                hideLoading()
            } catch (ex: HttpException) {

                ex.response()?.let { response ->
                    response.errorBody()?.let { responseBody ->
                        val msg = JSONObject(responseBody.string()).get("message")
                        _loginResponse.postValue(
                            (ErrorResource(
                                error = Error.NetworkErrors,
                                message = msg.toString()
                            ))
                        )
                    }
                }
                hideLoading()
            }
        }
    }


}