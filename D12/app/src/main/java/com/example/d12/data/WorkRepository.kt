package com.example.d12.data

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

interface WorkRepository {
    val outputWorkInfo: Flow<WorkInfo?>
    val periodicWorkInfo: Flow<WorkInfo?>

    fun applyWork(inputText: String, requiresNetwork: Boolean, requiresCharging: Boolean)
    fun schedulePeriodicWork()
    fun cancelWork()
}

class WorkManagerRepository(context: Context) : WorkRepository {

    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo?> =
        workManager.getWorkInfosForUniqueWorkFlow(WorkerConstants.ONE_TIME_WORK_NAME)
            .map { list -> list.firstOrNull() }

    override val periodicWorkInfo: Flow<WorkInfo?> =
        workManager.getWorkInfosForUniqueWorkFlow(WorkerConstants.PERIODIC_WORK_NAME)
            .map { list -> list.firstOrNull() }

    override fun applyWork(inputText: String, requiresNetwork: Boolean, requiresCharging: Boolean) {
        val constraintsBuilder = Constraints.Builder()
        if (requiresNetwork) {
            constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED)
        }
        if (requiresCharging) {
            constraintsBuilder.setRequiresCharging(true)
        }
        val constraints = constraintsBuilder.build()

        val inputData = workDataOf(
            WorkerConstants.KEY_INPUT_DATA to inputText
        )

        val workRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .addTag(WorkerConstants.TAG_SYNC_WORK)
            .build()

        workManager.enqueueUniqueWork(
            WorkerConstants.ONE_TIME_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun schedulePeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<PeriodicCleanupWorker>(
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .addTag(WorkerConstants.TAG_PERIODIC_WORK)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WorkerConstants.PERIODIC_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }

    override fun cancelWork() {
        workManager.cancelUniqueWork(WorkerConstants.ONE_TIME_WORK_NAME)
        workManager.cancelUniqueWork(WorkerConstants.PERIODIC_WORK_NAME)
    }
}
