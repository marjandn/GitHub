package dn.marjan.githubapp.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.preference.PreferenceManager
import dn.marjan.githubapp.ApplicationTest
import dn.marjan.githubapp.data.local.LocalDataServiceImp
import dn.marjan.githubapp.data.remote.RemoteDataService
import dn.marjan.githubapp.di.component.DaggerTestAppComponent
import dn.marjan.githubapp.di.component.TestAppComponent
import dn.marjan.githubapp.entity.UserInfo
import dn.marjan.githubapp.ui.login.repo.LoginRepositoryImp
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import javax.inject.Inject


class LoginRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var remoteDataService: RemoteDataService

    @Before
    fun setup() {
        val component: TestAppComponent =
            DaggerTestAppComponent.builder().application(ApplicationTest()).build()
        component.inject(this)
    }


    @Test
    fun `saveUserData, should save User info`() {
        // Given
        val userInfo: UserInfo = UserInfo()
        val sharedPreferences: SharedPreferences = mockkClass(SharedPreferences::class)
        val sharedPreferencesEditor: SharedPreferences.Editor = mockkClass(SharedPreferences.Editor::class)
        val context = mockk<Context>()
        every { context.packageName } returns "dn.marjan.githubapp"
        every { context.getSharedPreferences("dn.marjan.githubapp_preferences" , 0) } returns sharedPreferences

        every { PreferenceManager.getDefaultSharedPreferences(context) } returns sharedPreferences
        every { sharedPreferences.edit() } returns sharedPreferencesEditor
        justRun { sharedPreferencesEditor.putString(anyString() , anyString()) }


        // When
        val localD = LocalDataServiceImp(context)
        val rep = LoginRepositoryImp(remoteDataService, localD)
        rep.saveUserData(userInfo)

        // Then
        verify(exactly = 10) { sharedPreferencesEditor.putString(any(), any()) }
        verify(exactly = 10) { sharedPreferencesEditor.apply() }

    }


/*    @Test
    fun testtest(){
        val userInfo = mockk<UserInfo>()
        val sharedPreferences:SharedPreferences = mockk<SharedPreferences>()
        val sharedPreferencesEditor:SharedPreferences.Editor = mockk<SharedPreferences.Editor>()
        val context = mockk<Context>()

        every { context.packageName } returns "somePackageName"
        every { context.getSharedPreferences("somePackageName_preferences" , 0) } returns sharedPreferences
        every { PreferenceManager.getDefaultSharedPreferences(context) } returns sharedPreferences
        every { sharedPreferences.edit() } returns  sharedPreferencesEditor
        every { userInfo.login } returns "mm"
        justRun { sharedPreferencesEditor.putString(anyString() , anyString()) }


        val localD = LocalDataServiceImp(context)
        val rep = LoginRepositoryImp(remoteDataService, localD)
        val spyRepo = spyk(rep)

        spyRepo.saveUserData(userInfo)
        io.mockk.verify { sharedPreferencesEditor.putString(any(), "mm") }
        io.mockk.verify { sharedPreferencesEditor.apply() }

    }*/
}