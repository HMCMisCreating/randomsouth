package com.ebc.randomsouth.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ebc.randomsouth.EpisodesApplication
import com.ebc.randomsouth.data.EpisodesRepository
import com.ebc.randomsouth.models.Episode
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface EpisodeUiState {
    data class Success(val episodes: List<Episode>) : EpisodeUiState
    object Error : EpisodeUiState
    object Loading : EpisodeUiState
}

class EpisodesViewModel(private val episodesRepository: EpisodesRepository): ViewModel() {

    var episodesUiState: EpisodeUiState by mutableStateOf(EpisodeUiState.Loading)
        private set

    init {
        getEpisodes()
    }

    fun getEpisodes() {
        viewModelScope.launch {
            episodesUiState = EpisodeUiState.Loading
            episodesUiState = try {
                EpisodeUiState.Success(episodesRepository.getEpisode())
            } catch (e: IOException) {
                EpisodeUiState.Error
            } catch (e: HttpException) {
                EpisodeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as EpisodesApplication)
                val episodesRepository = application.container.episodesRepository
                EpisodesViewModel(episodesRepository = episodesRepository)
            }
        }
    }

}