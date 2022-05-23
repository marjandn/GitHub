package dn.marjan.githubapp.ui.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.local.LocalDataService
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.ui.home.repo.HomeRepository
import io.mockk.coEvery
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class HomeRepositoryTest {

    /*
        1- Test that username gave successfully
    */

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    @Inject
    lateinit var repository: HomeRepository

    @Inject
    lateinit var localDataService: LocalDataService

    @Inject
    lateinit var context: Context

    @Before
    fun setup(){
        val component: TestAppComponent = DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)
    }

    @Test
    fun `getUsername, getTrueUsername`(){
        // Given
        val fakeUsername = "some.username"
        coEvery { localDataService.getUsername()  } returns fakeUsername
        coEvery { repository.getUsername() } returns fakeUsername

        // When
        val username = repository.getUsername()

        // Then
        assertEquals(fakeUsername , username)
    }
}