package dn.marjan.githubapp.data.remote

import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.entity.UserInfo

interface RemoteDataService {
    suspend fun doLogin(): UserInfo

    suspend fun getReceivedEvents(username: String):  List<ReceivedEvents>

    suspend fun getRepositories(username: String): List<Repository>
}