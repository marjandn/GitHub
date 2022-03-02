package dn.marjan.githubapp.ui.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dn.marjan.githubapp.R


@Composable
fun ProfilePage(profileViewModel: ProfileViewModel) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, top = 32.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = profileViewModel.getUserImage(),
                builder = { crossfade(true) }), contentDescription = "Profile Image",
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth()
                .clip(CircleShape)
        )
        Text(
            text = profileViewModel.getFullName(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp)
                .fillMaxWidth()

        )
        Text(
            text = profileViewModel.getUsername(),
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_people),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                contentDescription = "Followers and Following",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "${profileViewModel.getFollowers()} followers . ${profileViewModel.getFollowing()} following",
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_link),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                contentDescription = "Blog",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = profileViewModel.getBlog(),
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                contentDescription = "Location",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = profileViewModel.getLocation(),
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_folder),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gray)),
                contentDescription = "Repos count",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = profileViewModel.getReposCount(),
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }

    }

}

@Preview
@Composable
fun PageContent() {

}