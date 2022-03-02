package dn.marjan.githubapp.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.di.AppComponent
import dn.marjan.githubapp.di.modules.AppModuleTest
import dn.marjan.githubapp.ui.home.HomeRepositoryTest
import dn.marjan.githubapp.ui.login.LoginRepositoryTest
import dn.marjan.githubapp.ui.home.HomeViewModelTest
import dn.marjan.githubapp.ui.login.LoginViewModelTest
import dn.marjan.githubapp.ui.repository.RepositoryViewModelTest
import dn.marjan.githubapp.ui.splash.SplashRepositoryTest
import dn.marjan.githubapp.ui.splash.SplashViewModelTest
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModuleTest::class
    ]
)
interface TestAppComponent: AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: ApplicationTest)
    fun inject(test: LoginViewModelTest)
    fun inject(test: LoginRepositoryTest)
    fun inject(test: HomeRepositoryTest)
    fun inject(test: HomeViewModelTest)
    fun inject(test: SplashViewModelTest)
    fun inject(test: SplashRepositoryTest)
    fun inject(test: RepositoryViewModelTest)

}