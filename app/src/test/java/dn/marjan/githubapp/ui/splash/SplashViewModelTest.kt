package dn.marjan.githubapp.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import dn.marjan.githubapp.ui.splash.ui.SplashViewModel
import io.mockk.coEvery
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var splashViewmodel: SplashViewModel

    @Inject
    lateinit var repository: SplashRepository

    @Mock
    lateinit var splashResponse: Observer<Resource<Boolean>>

    @Before
    fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)

        splashViewmodel.splashResponse.observeForever(splashResponse)
    }

    @Test
    fun `checkUserLogin, whenTrueResponse`() {
        // Given
        coEvery { repository.checkUserLogin() } returns true

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
        coEvery { repository.checkUserLogin() } returns false

        // When
        splashViewmodel.checkUserLogin()

        // Then
        Assert.assertTrue(splashViewmodel.splashResponse.value!! is ErrorResource)
        Assert.assertEquals(null , splashViewmodel.splashResponse.value!!.data)
        Assert.assertEquals(Error.EmptyResultError, splashViewmodel.splashResponse.value!!.error)

//        Mockito.verify(splashResponse).onChanged(Mockito.eq(ErrorResource(error = Error.EmptyResultError)))
    }
}