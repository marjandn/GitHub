package dn.marjan.githubapp.ui.repository.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dn.marjan.githubapp.R
import dn.marjan.githubapp.data.Status
import dn.marjan.githubapp.entity.Repository
import dn.marjan.githubapp.ui.dashboard.DashboardActivity
import dn.marjan.githubapp.utils.getLanguageColor
import dn.marjan.githubapp.utils.log

val repositories: MutableState<List<Repository>> = mutableStateOf(ArrayList())


@Composable
fun RepositoryPage(dashboard: DashboardActivity, viewModel: RepositoryViewModel) {

    PageContent()

    viewModel.repoResponse.observe(dashboard) {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.let { list ->
                    repositories.value = list
                }
            }
            else -> {
                log("sth has error")
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
        items(repositories.value) { item ->
            RepositoryItem(item)
        }

    }

}

// TODO: reDesign repositories item base of github
@Composable
fun RepositoryItem(item: Repository) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp, start = 16.dp)
    ) {
        Text(
            text = item.name ?: "",
            color = colorResource(id = R.color.purple_200),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),

            )
        item.description?.let {
            Text(
                text = it,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
        }
        Row(modifier = Modifier.padding(bottom = 16.dp) , verticalAlignment = Alignment.CenterVertically) {
            item.language?.let {
                Box(
                    modifier = Modifier
                        .size(17.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = getLanguageColor(it)))
                )
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 16.dp),

                    )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "star",
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            Text(
                text = item.starsCount ?: "",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 5.dp, end = 16.dp),

                )

            Image(
                painter = painterResource(id = R.drawable.fork),
                contentDescription = "fork",
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            Text(
                text = item.forkCounts ?: "",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 5.dp, end = 16.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_eye),
                contentDescription = "watch",
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            Text(
                text = item.watchersCount ?: "",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 5.dp, end = 16.dp),

                )
        }
        Divider(color = colorResource(id = R.color.gray), thickness = 0.5.dp)

    }
}