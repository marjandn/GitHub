package dn.marjan.githubapp.ui.repository.repo

import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.entity.Repository
import javax.inject.Inject

class RepoRepositoryImp @Inject constructor(
    val remoteDataService: RemoteDataService,
    val localDataService: LocalDataService
) : RepoRepository{

    override fun getUsername(): String = localDataService.getUsername()

    override suspend fun getRepositories(username: String): List<Repository> {
        return remoteDataService.getRepositories(username)
    }

}