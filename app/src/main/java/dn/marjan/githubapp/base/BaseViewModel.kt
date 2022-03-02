package dn.marjan.githubapp.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dn.marjan.githubapp.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel: ViewModel() {

    private val _showLoadingView = MutableLiveData<Boolean>()
    val showLoadingView:LiveData<Boolean> get() = _showLoadingView


    fun showLoading(){
        _showLoadingView.postValue(true)
    }
    fun hideLoading(){
        _showLoadingView.postValue(false)
    }


}