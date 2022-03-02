package dn.marjan.githubapp.ui.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dn.marjan.githubapp.R
import dn.marjan.githubapp.base.BaseActivity
import dn.marjan.githubapp.data.Resource
import dn.marjan.githubapp.data.Status
import dn.marjan.githubapp.ui.dashboard.DashboardActivity
import dn.marjan.githubapp.ui.login.ui.LoginActivity

class SplashActivity : BaseActivity<SplashViewModel>() {


    override fun getViewModel(): Class<SplashViewModel> = SplashViewModel::class.java

    @Composable
    override fun ProvideCompose() {
        ContentUI()

        viewModel.splashResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> startActivity(
                    Intent(
                        this,
                        DashboardActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                else -> startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            }

        }
    }

    @Preview
    @Composable
    fun ContentUI() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_github),
                contentDescription = "Github",
                modifier = Modifier.padding(all = 32.dp)
            )
        }
    }
}