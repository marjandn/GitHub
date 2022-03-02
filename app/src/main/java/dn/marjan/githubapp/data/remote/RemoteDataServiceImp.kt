package dn.marjan.githubapp.data.remote

import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.entity.UserInfo
import dn.marjan.githubapp.server.TaskService
import dn.marjan.githubapp.utils.Config
import javax.inject.Inject

class RemoteDataServiceImp @Inject constructor(val taskService: TaskService): RemoteDataService {

    override suspend fun doLogin(): UserInfo {
      return  taskService.login("Bearer ${Config.GITHUB_ACCESS_TOKEN}")
    }

    override suspend fun getReceivedEvents(username: String):  List<ReceivedEvents> {
        return  taskService.getReceivedEvents(username = username , page = "1") // TODO: dynamic page value with list Lazy Load
    }

    override suspend fun getRepositories(username: String): List<Repository> {
        return taskService.getRepositories(username = username , page = "1")
    }
}