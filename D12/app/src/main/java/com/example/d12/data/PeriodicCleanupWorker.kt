package com.example.d12.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

class PeriodicCleanupWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("PeriodicCleanupWorker", "Running scheduled periodic cleanup work")

        return try {
            delay(2.seconds)

            val timeFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault())
            val currentTime = timeFormat.format(Date())

            val outputData = workDataOf(
                WorkerConstants.KEY_OUTPUT_RESULT to "Đã thực hiện dọn dẹp & bảo trì hệ thống định kỳ thành công",
                WorkerConstants.KEY_OUTPUT_TIMESTAMP to currentTime,
                WorkerConstants.KEY_OUTPUT_PROCESSED_ITEMS to 100
            )

            Log.d("PeriodicCleanupWorker", "Periodic work finished successfully at $currentTime")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e("PeriodicCleanupWorker", "Error executing periodic cleanup", e)
            Result.failure()
        }
    }
}
