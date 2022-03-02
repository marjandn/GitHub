package dn.marjan.githubapp.ui.profile.ui

import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.ui.profile.repo.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val repository: ProfileRepository,
    val dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(){

      fun getFullName(): String = repository.getFullName()

      fun getUsername(): String = repository.getUsername()

      fun getFollowers(): String = repository.getFollowers()

      fun getFollowing(): String = repository.getFollowing()

      fun getBlog(): String = repository.getBlog()

      fun getLocation(): String = repository.getLocation()

      fun getReposCount(): String = repository.getReposCount()

      fun getUserImage(): String = repository.getUserImage()

}