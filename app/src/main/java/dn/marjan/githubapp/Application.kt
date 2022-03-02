package dn.marjan.githubapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dn.marjan.githubapp.di.DaggerAppComponent


open class Application : DaggerApplication() {

    open val applicationInjector= DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

}