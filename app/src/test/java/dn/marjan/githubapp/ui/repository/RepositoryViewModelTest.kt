package dn.marjan.githubapp.ui.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.ui.repository.repo.RepoRepository
import dn.marjan.githubapp.ui.repository.ui.RepositoryViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.justRun
import io.mockk.mockk
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class RepositoryViewModelTest {

    /*
        1- Test success response from fetch repositories
        2- Test failed response from fetch repositories
    */

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    @Inject
    lateinit var repoViewModel: RepositoryViewModel

    @Inject
    lateinit var repoRepository: RepoRepository

    @Before
    fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)
//        repoViewModel.repoResponse.observeForever(repoResponse)
    }

    @Test
    fun `onGetRepositories, SuccessResponse`(){
        // Given
        val fakeUsername = "fake.username"
        val reposList = mockk<List<Repository>>()
        every { repoRepository.getUsername() } returns fakeUsername
        coEvery { repoRepository.getRepositories(fakeUsername) } returns reposList

        // When
        repoViewModel.getRepositories()

        // Then
        Assert.assertTrue(repoViewModel.repoResponse.value is SuccessResource)
        Assert.assertEquals(null , repoViewModel.repoResponse.value!!.error)
        Assert.assertEquals(reposList , repoViewModel.repoResponse.value!!.data)
    }

    @Test
    fun `onGetRepositories, FailedResponse`() {
        // Given
        val fakeUsername = "fake.username"
        every { repoRepository.getUsername() } returns  fakeUsername
        coEvery { repoRepository.getRepositories(fakeUsername) } throws  HttpException(
            Response.error<HttpException>(
                500,
                ResponseBody.create(MediaType.parse("plain/text"), "{}")
            )
        )

        // When
        repoViewModel.getRepositories()

        // Then
        Assert.assertTrue(repoViewModel.repoResponse.value is ErrorResource)
        Assert.assertEquals(null , repoViewModel.repoResponse.value!!.data)
        Assert.assertEquals(Error.NetworkErrors , repoViewModel.repoResponse.value!!.error)
    }

}