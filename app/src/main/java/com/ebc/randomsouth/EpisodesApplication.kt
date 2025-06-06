package com.ebc.randomsouth

import android.app.Application
import com.ebc.randomsouth.data.EpisodesContainer
import com.ebc.randomsouth.data.DefaultEpisodesContainer

class EpisodesApplication: Application() {
    lateinit var container: EpisodesContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultEpisodesContainer()
    }
}