package dn.marjan.githubapp.ui.login.repo

import dn.marjan.githubapp.entity.UserInfo


interface LoginRepository {

   suspend fun loginReq(username: String , password: String): UserInfo

     fun saveUserData(user: UserInfo)

}