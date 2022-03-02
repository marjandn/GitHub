package dn.marjan.githubapp.ui.profile.repo

import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.entity.UserInfo
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    var localDataService: LocalDataService
) : ProfileRepository{
    override fun getFullName(): String = localDataService.getFullName()

    override fun getUsername(): String = localDataService.getUsername()

    override fun getFollowers(): String = localDataService.getFollowers()

    override fun getFollowing(): String = localDataService.getFollowing()

    override fun getBlog(): String = localDataService.getBlog()

    override fun getLocation(): String = localDataService.getLocation()

    override fun getReposCount(): String = localDataService.getReposCount()

    override fun getUserImage(): String = localDataService.getUserImage()


}