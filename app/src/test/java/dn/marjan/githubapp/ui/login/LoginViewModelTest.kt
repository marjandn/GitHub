package dn.marjan.githubapp.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.Error
import dn.marjan.githubapp.data.ErrorResource
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.SuccessResource
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.entity.UserInfo
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import dn.marjan.githubapp.ui.login.ui.LoginViewModel
import io.mockk.*
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    /**
            You may have noticed @get:Rule. This is a test rule. A test rule is a tool to change
            the way tests run, sometimes adding additional checks or running code before and
            after your tests. Android Architecture Components uses a background executor that
            is asynchronous to do its magic. InstantTaskExecutorRule is a rule that swaps out
            that executor and replaces it with synchronous one. This will make sure that, when
            you're using LiveData with the ViewModel, it's all run synchronously in the tests.
            more information:
            https://medium.com/@rlawlgns077/java-lang-runtimeexception-method-getmainlooper-in-android-os-looper-not-mocked-dbfe75ccabca
     **/
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var repository: LoginRepository

    @Mock
    lateinit var loginResponse: Observer<Resource<UserInfo>>


    @Before
    public fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)


        /*
                  You need a few mocked observers because the Activity will observe LiveData
                  objects exposed by the ViewModel. In the UI, you'll show a loading view when
                  retrieving the cocktails from the API and an error view if there's an error
                  retrieving the cocktails, score updates and questions. Because there's no lifecycle
                  here, you can use the observeForever() method.
        */
        loginViewModel.loginResponse.observeForever(loginResponse)
    }


    @Test
    fun `onSignInButtonClick , empty username and empty password, return EmptyInputError`() {
        // Given
        val username = ""
        val password = ""

        // When
        loginViewModel.validateLoginReq(username = username, password = password)

        // Then
        Assert.assertTrue(loginViewModel.loginResponse.value!! is ErrorResource)
        Assert.assertEquals(null , loginViewModel.loginResponse.value!!.data)
        Assert.assertEquals(Error.EmptyInputError, loginViewModel.loginResponse.value!!.error)

//        verify(loginResponse).onChanged(Mockito.eq(ErrorResource(error = Error.EmptyInputError)))
    }

    @Test
    fun `onSignInButtonClick, empty username and fill password, return EmptyInputError`() {
        //Given
        val username = ""
        val password = "123456"

        // When
        loginViewModel.validateLoginReq(username = username, password = password)

        // Then
        Assert.assertTrue(loginViewModel.loginResponse.value!! is ErrorResource)
        Assert.assertEquals(null , loginViewModel.loginResponse.value!!.data)
        Assert.assertEquals(Error.EmptyInputError, loginViewModel.loginResponse.value!!.error)

//        verify(loginResponse).onChanged(Mockito.eq(ErrorResource(error = Error.EmptyInputError)))
    }

    @Test
    fun `onSignInButtonClick, fill username and empty password, return EmptyInputError`() {
        // Given
        val username = "some.username"
        val password = ""

        // When
        loginViewModel.validateLoginReq(username = username, password = password)

        // Then
        Assert.assertTrue(loginViewModel.loginResponse.value!! is ErrorResource)
        Assert.assertEquals(null , loginViewModel.loginResponse.value!!.data)
        Assert.assertEquals(Error.EmptyInputError, loginViewModel.loginResponse.value!!.error)

//        verify(loginResponse).onChanged(Mockito.eq(ErrorResource(error = Error.EmptyInputError)))
    }

    @Test
    fun `onSignInButtonClick, fill username and fill password, return EmptyInputError`() {
        // Given
        val username = "some.username"
        val password = "123456"

        // When
        val spyVM = spy(loginViewModel)
        spyVM.validateLoginReq(username = username, password = password)

        // Then
        verify(spyVM).doLogin(username = username, password = password)
    }


    /*
         If our app has any suspend functions, we have to call it from another suspend function or launch it from a Coroutine. We can mitigate the problem by using the runBlocking()function.
         for more information: https://medium.com/swlh/unit-testing-with-kotlin-coroutines-the-android-way-19289838d257#id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IjE4MmU0NTBhMzVhMjA4MWZhYTFkOWFlMWQyZDc1YTBmMjNkOTFkZjgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2NDM3MDY4OTUsImF1ZCI6IjIxNjI5NjAzNTgzNC1rMWs2cWUwNjBzMnRwMmEyamFtNGxqZGNtczAwc3R0Zy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwODEwMjE3NDc4MDEzMzQzODc0NyIsImVtYWlsIjoibWFyamFuLmRuNzU5NkBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXpwIjoiMjE2Mjk2MDM1ODM0LWsxazZxZTA2MHMydHAyYTJqYW00bGpkY21zMDBzdHRnLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwibmFtZSI6Ik1hcmphbiBEbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQVRYQUp6QVhublVpMTFWR2UtdVVFTUY2bHJRdzFkckZZRUhHN1dwZndRPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Ik1hcmphbiIsImZhbWlseV9uYW1lIjoiRG4iLCJpYXQiOjE2NDM3MDcxOTUsImV4cCI6MTY0MzcxMDc5NSwianRpIjoiMzA2MzgxMDMyOWI5NjhkYTAyNDUyZDc0NmI5NDMyNmQxY2I2MzJlNyJ9.J--3S7Qet3FEbNOF6fanyxuFygPTAEJC-Yk4cYFo59nLpCWVJbGwPzyE8WWhuefpllkrVmLxv_X26a6YFzhTxr7CWQQYRwQxSkBLjrs6kH0rJVCxsEYYs_OvjMn02zRRfq9s6EW164bK7ikCelF9qYiPh2CQrzG3_tnO7VWOlSt-Msmu_uNfxD_ltlJASHc34Ce28OGBa120mxWAUXxxAdpTngL2Q9ap9__OjNFf8WoJU-lYJoYFQZWXz8JxIMS517RlAN3W_u4Qn4eW3IsMgz-yyUdzzA6SF69L7EBM6SsFAzVTgfibs5GXJcVgUpxJUz0W7WIEY7l3VPFekCZtUA
    */
    @Test
    fun `onSignInButtonClick, whenSuccessResponse`() {
        // Given
        val userInfo = mockk<UserInfo>()

        coEvery { repository.loginReq("", "") } returns userInfo
        coJustRun { repository.saveUserData(userInfo) }

        // When
        loginViewModel.doLogin("", "")


        // Then
        coVerifyOrder {
            repository.loginReq("", "")
            repository.saveUserData(userInfo)
        }


        Assert.assertTrue(loginViewModel.loginResponse.value!! is SuccessResource)
        Assert.assertEquals(userInfo , loginViewModel.loginResponse.value!!.data)
        Assert.assertEquals(null, loginViewModel.loginResponse.value!!.error)

//        verify(loginResponse).onChanged(eq(SuccessResource(data = userInfo)))
    }


    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")


    @Test
    fun `onSignInButtonClick, whenFailedResponse`() {

        // Given
        val error: HttpException = HttpException(
            Response.error<ResponseBody>(
                500, ResponseBody.create(
                    MediaType.parse("plain/text"), "{\"message\":\"testMessage\"}"
                )
            )
        )

        coEvery { repository.loginReq("", "") } throws error

        // When
        loginViewModel.doLogin("", "")

        // Then
        Assert.assertTrue(loginViewModel.loginResponse.value!! is ErrorResource)
        Assert.assertEquals(null , loginViewModel.loginResponse.value!!.data)
        Assert.assertEquals(Error.NetworkErrors, loginViewModel.loginResponse.value!!.error)

      /*  verify(loginResponse).onChanged(
            ErrorResource(
                error = Error.NetworkErrors,
                message = "testMessage"
            )
        )*/


    }
}