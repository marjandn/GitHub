package dn.marjan.githubapp

import dn.marjan.githubapp.di.AppComponent
import dn.marjan.githubapp.di.component.DaggerTestAppComponent

class ApplicationTest: Application() {

    override val applicationInjector: AppComponent
        get() = DaggerTestAppComponent.builder().application(this).build()
}