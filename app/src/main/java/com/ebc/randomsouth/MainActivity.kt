package com.ebc.randomsouth

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebc.randomsouth.databinding.ActivityMainBinding
import com.ebc.randomsouth.databinding.ActivityMainBinding.bind
import com.ebc.randomsouth.ui.theme.RandomsouthTheme
import com.ebc.randomsouth.viewmodels.EpisodesViewModel
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomsouthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val episodesViewModel: EpisodesViewModel =
                        viewModel(factory = EpisodesViewModel.Factory)


                    HomeScreen(
                        episodeUiState = episodesViewModel.episodesUiState,
                        retryAction = episodesViewModel::getEpisodes,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}

