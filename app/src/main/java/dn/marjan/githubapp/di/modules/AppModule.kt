package dn.marjan.githubapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dn.marjan.githubapp.base.AppCoroutineDispatcherProvider
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: android.app.Application):Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDispatchers(dispatcher: AppCoroutineDispatcherProvider): CoroutineDispatcherProvider = dispatcher
}