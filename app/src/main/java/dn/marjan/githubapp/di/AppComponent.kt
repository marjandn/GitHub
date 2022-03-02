package dn.marjan.githubapp.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dn.marjan.githubapp.Application
import dn.marjan.githubapp.di.modules.AppModule
import dn.marjan.githubapp.di.modules.NetworkModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
])
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: Application)
}