package dn.marjan.githubapp.ui.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    val repository: SplashRepository,
    val dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel() {

    private var _splashResponse = MutableLiveData<Resource<Boolean>>()

    val splashResponse: LiveData<Resource<Boolean>>
        get() = _splashResponse

    fun checkUserLogin(){

        viewModelScope.launch(dispatcherProvider.Main()) {
            val isUserLogin = repository.checkUserLogin()

            if(isUserLogin) _splashResponse.postValue(SuccessResource(data = true))
            else _splashResponse.postValue(ErrorResource(error = Error.EmptyResultError))
        }

    }
}