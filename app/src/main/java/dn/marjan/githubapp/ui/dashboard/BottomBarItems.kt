package dn.marjan.githubapp.ui.dashboard

import android.graphics.drawable.Drawable
import dn.marjan.githubapp.R

sealed class BottomBarItems(val name: String , val icon: Int, val route: String ) {
    object Home: BottomBarItems("Home" , R.drawable.ic_home , "home")
    object Repository: BottomBarItems("Repositories" , R.drawable.ic_folder , "repo")
    object Profile: BottomBarItems("Profile" , R.drawable.ic_account , "profile")
}