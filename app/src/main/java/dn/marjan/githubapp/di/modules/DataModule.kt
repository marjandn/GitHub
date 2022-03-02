package dn.marjan.githubapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.local.LocalDataServiceImp
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.data.remote.RemoteDataServiceImp
import dn.marjan.githubapp.server.TaskService
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataService(taskService: TaskService): RemoteDataService= RemoteDataServiceImp(taskService)

    @Singleton
    @Provides
    fun provideLocalDataService(context: Context): LocalDataService = LocalDataServiceImp(context)
}