package dn.marjan.githubapp.di.modules

import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.ui.home.repo.HomeRepository
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import dn.marjan.githubapp.ui.profile.repo.ProfileRepository
import dn.marjan.githubapp.ui.repository.repo.RepoRepository
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import dn.marjan.githubapp.ui.splash.repo.SplashRepositoryImp
import io.mockk.mockk
import io.mockk.spyk
import javax.inject.Singleton

@Module
class RepositoryModuleTest {

    @Singleton
    @Provides
    fun provideProfileRepository(
        localDataService: LocalDataService,
    ): ProfileRepository = mockk()

    @Singleton
    @Provides
    fun provideLoginRepository(
        localDataService: LocalDataService,
        remoteDataService: RemoteDataService
    ): LoginRepository = mockk()

    @Singleton
    @Provides
    fun provideHomeRepository(
        remoteDataService: RemoteDataService ,
        localDataService: LocalDataService
    ): HomeRepository = mockk()


    @Singleton
    @Provides
    fun provideRepoRepository(
        remoteDataService: RemoteDataService ,
        localDataService: LocalDataService
    ): RepoRepository = mockk()

    @Singleton
    @Provides
    fun provideSplashRepository(
        localDataService: LocalDataService
    ): SplashRepository = mockk( )
}