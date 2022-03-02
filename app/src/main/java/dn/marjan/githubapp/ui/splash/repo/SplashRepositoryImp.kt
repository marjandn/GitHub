package dn.marjan.githubapp.ui.splash.repo

import dn.marjan.githubapp.data.local.LocalDataService
import javax.inject.Inject

class SplashRepositoryImp @Inject constructor(
    val localDataService: LocalDataService
) : SplashRepository{

    override fun checkUserLogin(): Boolean {
      return  localDataService.isUserLogin()
    }

}