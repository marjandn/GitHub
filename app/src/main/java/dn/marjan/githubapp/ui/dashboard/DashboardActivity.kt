package dn.marjan.githubapp.ui.dashboard

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dn.marjan.githubapp.R
import dn.marjan.githubapp.base.BaseActivity
import dn.marjan.githubapp.ui.home.ui.HomePage
import dn.marjan.githubapp.ui.home.ui.HomeViewModel
import dn.marjan.githubapp.ui.profile.ui.ProfilePage
import dn.marjan.githubapp.ui.profile.ui.ProfileViewModel
import dn.marjan.githubapp.ui.repository.ui.RepositoryPage
import dn.marjan.githubapp.ui.repository.ui.RepositoryViewModel
import javax.inject.Inject

class DashboardActivity : BaseActivity<DashboardViewModel>() {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var repositoryViewModel: RepositoryViewModel
    @Inject
    lateinit var profiViewModel: ProfileViewModel

    private lateinit var pageTitle: MutableState<String>


    override fun getViewModel(): Class<DashboardViewModel> = DashboardViewModel::class.java

    private val items = listOf<BottomBarItems>(
        BottomBarItems.Home,
        BottomBarItems.Repository,
        BottomBarItems.Profile
    )


    @Composable
    override fun ProvideCompose() {
        val navController = rememberNavController()
        pageTitle = remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = pageTitle.value, color = Color.White) },
                    backgroundColor =  colorResource(R.color.black),
                )
            },
            bottomBar = { BottomBar(navController = navController) },
            backgroundColor =colorResource(R.color.black)
        ) {
            CurrentPage(navController)
        }
    }


    @Composable
    fun BottomBar(navController: NavController) {
        BottomNavigation(
            backgroundColor = colorResource(R.color.black),
            contentColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name
                        )
                    },
                    label = { Text(text = item.name) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(item.route) {

                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun CurrentPage(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomBarItems.Home.route,
            builder = {

                composable(BottomBarItems.Home.route) {
                    pageTitle.value = BottomBarItems.Home.name
                    HomePage(this@DashboardActivity, homeViewModel)
                }
                composable(BottomBarItems.Repository.route) {
                    pageTitle.value = BottomBarItems.Repository.name
                    RepositoryPage(this@DashboardActivity, repositoryViewModel)
                }
                composable(BottomBarItems.Profile.route) {
                    pageTitle.value = BottomBarItems.Profile.name
                    ProfilePage(  profiViewModel)
                }
            })
    }
}