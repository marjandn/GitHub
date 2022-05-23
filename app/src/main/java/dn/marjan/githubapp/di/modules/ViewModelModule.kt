package dn.marjan.githubapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dn.marjan.githubapp.base.ViewModelFactory
import dn.marjan.githubapp.di.keys.ViewModelKey
import dn.marjan.githubapp.ui.dashboard.DashboardViewModel
import dn.marjan.githubapp.ui.home.ui.HomeViewModel
import dn.marjan.githubapp.ui.login.ui.LoginViewModel
import dn.marjan.githubapp.ui.profile.ui.ProfileViewModel
import dn.marjan.githubapp.ui.repository.ui.RepositoryViewModel
import dn.marjan.githubapp.ui.splash.ui.SplashViewModel
import javax.inject.Provider


/**
Note that the return type of the provider method is ViewModel, not ViewModel1. It’s intentional.
@IntoMap annotation says that Provider object for this service will be inserted into Map, and @ViewModelKey annotation specifies under which
key it will reside.
The net result of the above code will be that Dagger will create Map data structure
filled with Provider<ViewModel> objects and then provide it implicitly to other services.
 **/


@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardActivityVM(dashboardViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeVM(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginActivityVM(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashActivityVM(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    abstract fun bindRepositoryVM(repositoryViewModel: RepositoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileVM(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    internal  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
