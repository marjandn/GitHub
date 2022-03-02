package dn.marjan.githubapp.ui.login.ui

import android.content.Intent
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dn.marjan.githubapp.R
import dn.marjan.githubapp.base.BaseActivity
import dn.marjan.githubapp.data.Status
import dn.marjan.githubapp.ui.dashboard.DashboardActivity
import kotlinx.coroutines.launch

/*
   Google: If you use another observable type such as LiveData in Compose, you should convert it to State<T> before reading it in a composable
   using a composable extension function like LiveData<T>.observeAsState().
*/

class LoginActivity : BaseActivity<LoginViewModel>() {


    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    @Composable
    override fun ProvideCompose() {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()


        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }



        viewModel.loginResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    startActivity(
                        Intent(
                            this,
                            DashboardActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                }
                else -> {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it.message.toString(),
                            actionLabel = "OK"
                        )
                    }
                }
            }
        }




        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            MainView(
                username = username,
                onUsernameChange = { username = it },
                password = password,
                onPasswordChange = { password = it },
                onLoginButtonClick = { viewModel.validateLoginReq(username, password) })
        }

    }


    @Composable
    fun MainView(
        username: String,
        onUsernameChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        onLoginButtonClick: () -> Unit
    ) {
        LazyColumn(content = {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize() ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "Github",
                        Modifier
                            .padding(end = 32.dp, start = 32.dp, top = 50.dp, bottom = 40.dp)
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = onUsernameChange,
                        label = { Text(text = "Username / email") },
                        modifier = Modifier
                            .padding(end = 32.dp, start = 32.dp, bottom = 12.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text(text = "Password") },
                        modifier = Modifier
                            .padding(end = 32.dp, start = 32.dp, bottom = 32.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = onLoginButtonClick,
                        modifier = Modifier
                            .padding(end = 32.dp, start = 32.dp, bottom = 12.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(9.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.black))
                    ) {
                        Text(
                            text = "Sign in", color = Color.White, fontSize = 18.sp, modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }
        })

    }
}