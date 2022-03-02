package dn.marjan.githubapp.ui.splash

import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.ui.splash.repo.SplashRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class SplashRepositoryTest {

    @Inject
    lateinit var repository: SplashRepository

    @Inject
    lateinit var localDataService: LocalDataService

    @Before
    fun setup(){
        val component: TestAppComponent = DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)

    }

    @Test
    fun `checkUserLogin, whenTrueResponse`(){
        // Given
        coEvery {  localDataService.isUserLogin() } returns true
        coEvery { repository.checkUserLogin() } returns true

        // When
        val isUserLogin = repository.checkUserLogin()

        // Then
        assertTrue(isUserLogin)
    }

}