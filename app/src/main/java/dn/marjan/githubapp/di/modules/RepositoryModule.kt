package dn.marjan.githubapp.di.modules

import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.ui.home.repo.HomeRepository
import dn.marjan.githubapp.ui.home.repo.HomeRepositoryImp
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import dn.marjan.githubapp.ui.login.repo.LoginRepositoryImp
import dn.marjan.githubapp.ui.profile.repo.ProfileRepository
import dn.marjan.githubapp.ui.profile.repo.ProfileRepositoryImp
import dn.marjan.githubapp.ui.repository.repo.RepoRepository
import dn.marjan.githubapp.ui.repository.repo.RepoRepositoryImp
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import dn.marjan.githubapp.ui.splash.repo.SplashRepositoryImp
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProfileRepository(localDataService: LocalDataService): ProfileRepository =
        ProfileRepositoryImp(localDataService = localDataService)

    @Singleton
    @Provides
    fun provideLoginRepository(
        localDataService: LocalDataService,
        remoteDataService: RemoteDataService
    ): LoginRepository =
        LoginRepositoryImp(remoteDataService, localDataService)

    @Singleton
    @Provides
    fun provideHomeRepository(
        localDataService: LocalDataService,
        remoteDataService: RemoteDataService
    ): HomeRepository =
        HomeRepositoryImp(remoteDataService, localDataService)

    @Singleton
    @Provides
    fun provideRepoRepository(
        localDataService: LocalDataService,
        remoteDataService: RemoteDataService
    ): RepoRepository =
        RepoRepositoryImp(remoteDataService, localDataService)

    @Singleton
    @Provides
    fun provideSplashRepository(localDataService: LocalDataService): SplashRepository =
        SplashRepositoryImp(localDataService = localDataService)

}