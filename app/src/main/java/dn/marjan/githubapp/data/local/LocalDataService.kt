package dn.marjan.githubapp.data.local

import dn.marjan.githubapp.entity.UserInfo

interface LocalDataService {

      fun saveUserData(user: UserInfo)

      fun getFullName():String
      fun getUsername():String
      fun getFollowers():String
      fun getFollowing():String
      fun getBlog():String
      fun getLocation():String
      fun getReposCount():String
      fun getUserImage():String

      fun isUserLogin(): Boolean

}