package br.com.luanadev.ceepapplication

import android.app.Application
import br.com.luanadev.ceepapplication.data.source.TasksRepository
import data.ServiceLocator
import timber.log.Timber
import timber.log.Timber.DebugTree

class TodoApplication : Application() {

    val taskRepository: TasksRepository
        get() = ServiceLocator.provideTasksRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
