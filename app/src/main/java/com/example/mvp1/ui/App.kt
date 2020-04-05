package com.example.mvp1.ui

import android.app.Application
import com.example.mvp1.BuildConfig
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import timber.log.Timber.DebugTree


class App:Application() {

    companion object{
        lateinit var instance:App
    }

    val cicerone:Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router
}