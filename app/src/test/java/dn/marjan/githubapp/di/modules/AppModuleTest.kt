package dn.marjan.githubapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.di.dispatchers.TestCoroutineDispatcherProvider
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module(
    includes = [
        DataModuleTest::class,
        RepositoryModuleTest::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)
class AppModuleTest {
    @Singleton
    @Provides
    fun provideContext(application: android.app.Application): Context = mockk()


    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideDispatchers(dispatcher: TestCoroutineDispatcherProvider): CoroutineDispatcherProvider = dispatcher

}