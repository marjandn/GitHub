package dn.marjan.githubapp.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class AppCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider

interface CoroutineDispatcherProvider {

    fun IO(): CoroutineDispatcher = Dispatchers.IO

    fun Main(): CoroutineDispatcher = Dispatchers.Main

}