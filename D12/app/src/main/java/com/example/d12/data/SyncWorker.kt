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

class SyncWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val input = inputData.getString(WorkerConstants.KEY_INPUT_DATA) ?: "Nhiệm vụ mặc định"
        Log.d("SyncWorker", "Bắt đầu xử lý công việc ngầm: $input")

        return try {
            // Giả lập các bước xử lý công việc ngầm
            delay(3.seconds)

            val timeFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault())
            val currentTime = timeFormat.format(Date())

            val outputData = workDataOf(
                WorkerConstants.KEY_OUTPUT_RESULT to "Đã xử lý thành công công việc '$input'",
                WorkerConstants.KEY_OUTPUT_TIMESTAMP to currentTime,
                WorkerConstants.KEY_OUTPUT_PROCESSED_ITEMS to 42
            )

            Log.d("SyncWorker", "Công việc hoàn thành thành công: $input")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e("SyncWorker", "Error executing sync work", e)
            Result.failure()
        }
    }
}
