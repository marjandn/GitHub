package dn.marjan.githubapp.ui.home.repo

import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.entity.ReceivedEvents
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
   val remoteDataService: RemoteDataService,
    val localDataService: LocalDataService
) : HomeRepository {

    override suspend fun getReceivedEvents(username: String):  List<ReceivedEvents> {
      return remoteDataService.getReceivedEvents(username)
    }

    override fun getUsername(): String {
     return   localDataService.getUsername()
    }
}