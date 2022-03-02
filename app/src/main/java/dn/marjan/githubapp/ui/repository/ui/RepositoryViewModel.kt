package dn.marjan.githubapp.ui.repository.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.ui.repository.repo.RepoRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    val repoRepository: RepoRepository,
    val dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel() {

    private val _repoResponse = MutableLiveData<Resource<List<Repository>>>()

    val repoResponse: LiveData<Resource<List<Repository>>>
        get() = _repoResponse

    init {
        getRepositories()
    }

    fun getRepositories(){
        viewModelScope.launch(dispatcherProvider.IO()) {
            try{
                val username = repoRepository.getUsername()
                val dd = repoRepository.getRepositories(username = username)
                _repoResponse.postValue(SuccessResource(data =dd ))
            }catch (e: HttpException){
                _repoResponse.postValue(ErrorResource(error = Error.NetworkErrors , message = e.message()))
            }
        }
    }
}