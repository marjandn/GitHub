package dn.marjan.githubapp.ui.login.repo

import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.entity.UserInfo
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    val remoteDataService: RemoteDataService,
    val localDataService: LocalDataService
) : LoginRepository {

    override suspend fun loginReq(username: String, password: String): UserInfo {
        return remoteDataService.doLogin()
    }

    override fun saveUserData(user: UserInfo) {
        localDataService.saveUserData(user)
    }

}