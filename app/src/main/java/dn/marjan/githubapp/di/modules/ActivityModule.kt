package dn.marjan.githubapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dn.marjan.githubapp.ui.dashboard.DashboardActivity
import dn.marjan.githubapp.ui.login.ui.LoginActivity
import dn.marjan.githubapp.ui.splash.ui.SplashActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector
    abstract fun bindSplashAcitvity(): SplashActivity
}
