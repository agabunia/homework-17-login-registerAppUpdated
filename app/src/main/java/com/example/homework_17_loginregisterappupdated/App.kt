package com.example.homework_17_loginregisterappupdated

import android.app.Application

class App : Application() {
    companion object {
        lateinit var application: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
