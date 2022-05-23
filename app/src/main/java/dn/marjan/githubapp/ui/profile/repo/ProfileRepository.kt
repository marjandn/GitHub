package dn.marjan.githubapp.ui.profile.repo


interface ProfileRepository {

    fun getFullName():String
    fun getUsername():String
    fun getFollowers():String
    fun getFollowing():String
    fun getBlog():String
    fun getLocation():String
    fun getReposCount():String
    fun getUserImage():String
}