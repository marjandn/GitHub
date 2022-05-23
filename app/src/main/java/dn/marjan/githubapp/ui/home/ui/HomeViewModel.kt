package dn.marjan.githubapp.ui.home.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.data.*
import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.ui.home.repo.HomeRepository
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import dn.marjan.githubapp.utils.log
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val repository: HomeRepository,
    val dispatcher: CoroutineDispatcherProvider
) : BaseViewModel() {


    private val _homeResponse = MutableLiveData<Resource<List<ReceivedEvents>>>()


    val homeResponse: LiveData<Resource<List<ReceivedEvents>>>
        get() = _homeResponse

    fun getReceivedEvents(){
        viewModelScope.launch(dispatcher.IO()){
            showLoading()
            try{
                _homeResponse.postValue(SuccessResource(data = repository.getReceivedEvents(username = repository.getUsername())))
                hideLoading()

            }catch (ex: HttpException){
                _homeResponse.postValue(ErrorResource(error = Error.NetworkErrors, message = ex.message().toString()))
            }
        }
    }

    fun justLog() {
        log("WE RE RENDER HOME PAGE MARJAN :)")
    }
}