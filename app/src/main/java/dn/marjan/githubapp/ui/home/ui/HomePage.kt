package dn.marjan.githubapp.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dn.marjan.githubapp.data.Status
import dn.marjan.githubapp.entity.ReceivedEvents
import dn.marjan.githubapp.ui.dashboard.DashboardActivity
import dn.marjan.githubapp.utils.log
import androidx.compose.foundation.lazy.items
import dn.marjan.githubapp.utils.getAction

val receivedEvents: MutableState<List<ReceivedEvents>> = mutableStateOf(ArrayList())

@Composable
fun HomePage(dashboard: DashboardActivity, viewModel: HomeViewModel) {
    viewModel.justLog()

    PageContent()


    viewModel.homeResponse.observe(dashboard) {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.let { list ->
                    val items: List<ReceivedEvents> = (list)
                    receivedEvents.value = items
                }
            }
            else -> {
                log("some error")
            }
        }
    }
}


@Preview
@Composable
fun PageContent() {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 40.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        items(receivedEvents.value){item ->
            EventItem(item)
        }
    }
}

// TODO: change UI of Event items
@Composable
fun EventItem(item: ReceivedEvents) {
    return Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = item.actor?.avatar_url,
                builder = {
                    crossfade(true)
                },
            ), contentDescription = "User avatar image",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(item.actor?.login.toString())
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 15.sp)) {
                    append(" ${getAction(item)} ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(item.repo?.name.toString())
                }
            },
            modifier = Modifier
                .padding(top = 10.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}