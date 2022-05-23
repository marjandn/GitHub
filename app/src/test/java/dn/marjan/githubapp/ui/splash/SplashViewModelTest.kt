package dn.marjan.githubapp.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import dn.marjan.githubapp.ui.splash.ui.SplashViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

class SplashViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var splashViewmodel: SplashViewModel

    @Inject
    lateinit var repository: SplashRepository

    @Before
    fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)
    }

    @Test
    fun `checkUserLogin, whenTrueResponse`() {
        // Given
        every { repository.checkUserLogin( ) } returns true

        // When
        splashViewmodel.checkUserLogin()

        // Then
        Assert.assertTrue(splashViewmodel.splashResponse.value!! is SuccessResource)
        Assert.assertEquals(true , splashViewmodel.splashResponse.value!!.data)
        Assert.assertEquals(null, splashViewmodel.splashResponse.value!!.error)

//        Mockito.verify(splashResponse).onChanged(Mockito.eq(SuccessResource(data = true)))
    }

    @Test
    fun `checkUserLogin, whenFalseResponse`() {
        // Given
        every { repository.checkUserLogin() } returns false

        // When
        splashViewmodel.checkUserLogin()

        // Then
        Assert.assertTrue(splashViewmodel.splashResponse.value!! is ErrorResource)
        Assert.assertEquals(null , splashViewmodel.splashResponse.value!!.data)
        Assert.assertEquals(Error.EmptyResultError, splashViewmodel.splashResponse.value!!.error)

//        Mockito.verify(splashResponse).onChanged(Mockito.eq(ErrorResource(error = Error.EmptyResultError)))
    }
}