package com.ebc.randomsouthpark


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ebc.randomsouthpark.ui.components.EpisodeScreen
import com.ebc.randomsouthpark.ui.theme.RandomsouthTheme


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

