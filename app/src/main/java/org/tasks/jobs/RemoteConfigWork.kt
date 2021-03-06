package org.tasks.jobs

import android.content.Context
import androidx.work.WorkerParameters
import org.tasks.injection.ApplicationComponent
import org.tasks.injection.InjectingWorker

class RemoteConfigWork(context: Context, workerParams: WorkerParameters) : InjectingWorker(context, workerParams) {
    override fun run(): Result {
        firebase.updateRemoteConfig()
        return Result.success()
    }

    override fun inject(component: ApplicationComponent) = component.inject(this)
}