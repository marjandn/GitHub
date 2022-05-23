package dn.marjan.githubapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.data.local.LocalDataServiceImp
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.server.TaskService
import io.mockk.mockk
import io.mockk.spyk
import javax.inject.Singleton

@Module
object DataModuleTest {

    @Singleton
    @Provides
    fun provideRemoteDataService(taskService: TaskService): RemoteDataService = mockk()


    @Singleton
    @Provides
    fun provideLocalDataService(): LocalDataService = mockk( )

}