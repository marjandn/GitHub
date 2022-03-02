package dn.marjan.githubapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.ui.home.repo.HomeRepository
import dn.marjan.githubapp.ui.home.ui.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    /*
        1- Test for get success events response
        2- Test for get failed events response
    */

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var repository: HomeRepository

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var homeResponse: Observer<Resource<List<ReceivedEvents>>>

    @Before
    fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)

        homeViewModel.homeResponse.observeForever(homeResponse)
    }

    @Test
    fun `getReceivedEvents, whenSuccessResponse`() {
        // Given
        val events = mockk< List<ReceivedEvents>>()

        val fakeUsername = "some.username"

        // repository is mockk so we have to use coEvery, not Mockito.when
        coEvery { repository.getUsername() } returns fakeUsername
        coEvery { repository.getReceivedEvents(fakeUsername) } returns events

        // When
        homeViewModel.getReceivedEvents()

        //Then
        Assert.assertTrue(homeViewModel.homeResponse.value!! is SuccessResource)
        Assert.assertEquals(events , homeViewModel.homeResponse.value!!.data)
        Assert.assertEquals(null, homeViewModel.homeResponse.value!!.error)

//        Mockito.verify(homeResponse).onChanged(Mockito.eq(SuccessResource(data = events)))
    }

    @Test
    fun `getReceivedEvents, whenFailedResponse`() {
        // Given
        val fakeUsername = "some.username"
        val httpException  =HttpException(
            Response.error<HttpException>(
                500,
                ResponseBody.create(MediaType.parse("plain/text"), "{}")
            )
        )

        coEvery { repository.getUsername() } returns fakeUsername
        coEvery { repository.getReceivedEvents(fakeUsername) } throws httpException

        // When
        homeViewModel.getReceivedEvents()

        //Then
        Assert.assertTrue(homeViewModel.homeResponse.value!! is ErrorResource)
        Assert.assertEquals(null , homeViewModel.homeResponse.value!!.data)
        Assert.assertEquals(Error.NetworkErrors, homeViewModel.homeResponse.value!!.error)

//        Mockito.verify(homeResponse).onChanged(Mockito.eq(ErrorResource(error = Error.NetworkErrors , message = httpException.message())))

    }
}