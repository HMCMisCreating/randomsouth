package com.ebc.randomsouth


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import androidx.compose.foundation.Image
import com.ebc.randomsouth.models.EpisodeData
import com.ebc.randomsouth.ui.theme.AppTypography
import com.ebc.randomsouth.ui.theme.RandomsouthTheme

import com.ebc.randomsouth.viewmodels.EpisodeViewModel
import java.net.URLEncoder


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomsouthTheme {
                EpisodeScreen()
            }
        }
    }
}


@Composable
fun EpisodeDetails(episode: EpisodeData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = episode.name,
            style = AppTypography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        // Display thumbnail if available
        //episode.imgSrc?.let { imgSrc ->

        AsyncImage(
            model =  episode.imgSrc, // Just pass the URL directly
            contentDescription = "Episode thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_launcher_background)
            )


        Text(text = "Season ${episode.season}, Episode ${episode.episode}")

        episode.description?.let { description ->
            Text(
                text = description,
                style = AppTypography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

    }
}


@Composable
fun EpisodeScreen() {
    val viewModel: EpisodeViewModel = viewModel()
    val episode by viewModel.episodeLiveData.observeAsState()

    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.homemainbg),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )


        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text(text = error!!, color = MaterialTheme.colorScheme.error)
                episode == null -> Text("PRESS THE BUTTON")
                else -> EpisodeDetails(episode = episode!!)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchRandomEpisode() },
                enabled = !isLoading,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("GENERATE")
            }
        }

    }

}

