package com.dynamo.task.util

import android.app.Application
import com.dynamo.task.data.api.ApiModule
import dagger.ObjectGraph

class App : Application() {

    private lateinit var objectGraph: ObjectGraph

    override fun onCreate() {
        super.onCreate()
        buildObjectGraphAndInject()
    }

    fun inject(instance: Any) {
        objectGraph.inject(instance)
    }

    private fun buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(ApiModule())
        inject(this)
    }
}
