package dn.marjan.githubapp.server

import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.entity.UserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface TaskService {

    @GET("user")
    suspend fun login(
        @Header("Authorization") auth: String
    ) : UserInfo

    @GET("users/{username}/received_events?")
    suspend fun getReceivedEvents(
        @Path("username") username: String,
        @Query("page") page: String
    ) : List<ReceivedEvents>

    @GET("users/{username}/repos?")
    suspend fun getRepositories(
        @Path("username") username: String,
        @Query("page") page: String
    ) : List<Repository>

}