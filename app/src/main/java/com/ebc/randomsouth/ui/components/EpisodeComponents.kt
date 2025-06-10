package com.ebc.randomsouth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ebc.randomsouth.R
import com.ebc.randomsouth.models.EpisodeData
import com.ebc.randomsouth.ui.theme.AppTypography
import com.ebc.randomsouth.ui.theme.Grey40
import com.ebc.randomsouth.ui.theme.Red10
import com.ebc.randomsouth.viewmodels.EpisodeViewModel


@Composable
fun EpisodeDetails(episode: EpisodeData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = episode.name,
            fontSize = 24.sp,
            style = AppTypography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(all = 10.dp)
                .background(Grey40)
                .padding(all = 16.dp)
                .fillMaxWidth()
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


        Text(text = "Season ${episode.season}, Episode ${episode.episode}",
            style = AppTypography.titleMedium,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(all = 10.dp)
                .background(Grey40)
                .padding(all = 16.dp)
                .fillMaxWidth()
        )

        episode.description?.let { description ->
            Text(
                text = description,
                style = AppTypography.labelLarge,
                modifier = Modifier.padding(vertical = 10.dp)
                    .background(Grey40)
                    .padding(all = 16.dp)
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
                episode == null -> Text(text = "PRESS THE BUTTON!!",
                    style = AppTypography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 10.dp)
                        .background(Grey40)
                        .padding(all = 16.dp))
                else -> EpisodeDetails(episode = episode!!)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchRandomEpisode() },
                enabled = !isLoading,
                modifier = Modifier.padding(16.dp)
                    .size(90.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red10,
                    contentColor = Color.Black)
            ) {
                Text(text="GENERATE",
                    fontWeight = FontWeight.Bold)
            }
        }

    }

}

