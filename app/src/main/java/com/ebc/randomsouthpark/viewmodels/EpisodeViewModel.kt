package com.ebc.randomsouthpark.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.randomsouthpark.data.EpisodeRepository
import com.ebc.randomsouthpark.models.EpisodeData
import kotlinx.coroutines.launch


class EpisodeViewModel : ViewModel() {
    private val repository = EpisodeRepository()
    private val _episodeLiveData = MutableLiveData<EpisodeData?>()
    private val _isLoading = MutableLiveData(false)
    private val _error = MutableLiveData<String?>(null)


    val episodeLiveData: LiveData<EpisodeData?> = _episodeLiveData
    val isLoading: LiveData<Boolean> = _isLoading
    val error: LiveData<String?> = _error

    fun fetchRandomEpisode() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val episode = repository.getRandomEpisode()
                _episodeLiveData.value = episode ?: EpisodeData(
                    name = "No episode found",
                    id = 0,
                    season = 0,
                    episode = 0,
                    airDate = "NOPE",
                    wikiSrc = "GO ASK WIKIPEDIA ASSHOLE",
                    imgSrc = "https://wallpapercave.com/wp/BFzMt5c.jpg",
                    description = "NADA",
                    created = "NOTHING",
                    updated = "YOU SUCK",
                    characters = listOf("WHY YOU ASK","WHY U CARE"),
                    locations = listOf("WHY YOU ASK","WHY U CARE")
                )

            } catch (e: Exception) {
                _error.value = "Failed to load episode: ${e.message}"
                _episodeLiveData.value = EpisodeData(
                    name = "No episode found",
                    id = 0,
                    season = 0,
                    episode = 0,
                    airDate = "NOPE",
                    wikiSrc = "GO ASK WIKIPEDIA ASSHOLE",
                    imgSrc = "https://wallpapercave.com/wp/BFzMt5c.jpg",
                    description = "NADA",
                    created = "NOTHING",
                    updated = "YOU SUCK",
                    characters = listOf("WHY YOU ASK","WHY U CARE"),
                    locations = listOf("WHY YOU ASK","WHY U CARE")
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}
