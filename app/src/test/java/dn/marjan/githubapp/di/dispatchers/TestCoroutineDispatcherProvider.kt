package dn.marjan.githubapp.di.dispatchers

import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TestCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    val testDispatcher = UnconfinedTestDispatcher()

    override fun IO(): CoroutineDispatcher = testDispatcher

    override fun Main(): CoroutineDispatcher = testDispatcher
}