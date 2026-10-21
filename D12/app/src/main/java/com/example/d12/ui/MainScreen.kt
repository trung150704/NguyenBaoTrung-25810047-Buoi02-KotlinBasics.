package com.example.d12.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.WorkInfo
import com.example.d12.data.WorkerConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val oneTimeWorkInfo by viewModel.oneTimeWorkInfo.collectAsState()
    val periodicWorkInfo by viewModel.periodicWorkInfo.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bài học 12: WorkManager & Repository", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Phần 1: Cấu hình Input Dữ liệu & Ràng buộc Constraints
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "1. Dữ liệu Đầu vào & Điều kiện Ràng buộc",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = uiState.inputText,
                        onValueChange = { viewModel.updateInputText(it) },
                        label = { Text("Dữ liệu Đầu vào (Tên Nhiệm vụ)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    HorizontalDivider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Yêu cầu Mạng Internet", fontWeight = FontWeight.SemiBold)
                            Text("Chỉ chạy khi thiết bị có kết nối mạng", style = MaterialTheme.typography.bodySmall)
                        }
                        Switch(
                            checked = uiState.requiresNetwork,
                            onCheckedChange = { viewModel.updateRequiresNetwork(it) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Yêu cầu Đang Sạc Pin", fontWeight = FontWeight.SemiBold)
                            Text("Chỉ chạy khi thiết bị đang được cắm sạc", style = MaterialTheme.typography.bodySmall)
                        }
                        Switch(
                            checked = uiState.requiresCharging,
                            onCheckedChange = { viewModel.updateRequiresCharging(it) }
                        )
                    }
                }
            }

            // Phần 2: Điều khiển Yêu cầu Công việc
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "2. Lập lịch Nhiệm vụ (Work Requests)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.startOneTimeWork() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Nhiệm vụ 1 Lần")
                        }

                        Button(
                            onClick = { viewModel.startPeriodicWork() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Nhiệm vụ Định Kỳ")
                        }
                    }

                    OutlinedButton(
                        onClick = { viewModel.cancelAllWork() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Hủy Tất Cả Nhiệm Vụ")
                    }
                }
            }

            // Phần 3: Trạng thái & Kết quả Dữ liệu Đầu ra cho Nhiệm vụ 1 Lần
            WorkStatusCard(
                title = "3. Trạng thái Nhiệm vụ 1 Lần (SyncWorker)",
                workInfo = oneTimeWorkInfo
            )

            // Phần 4: Trạng thái Nhiệm vụ Định Kỳ
            WorkStatusCard(
                title = "4. Trạng thái Nhiệm vụ Định Kỳ (PeriodicCleanupWorker)",
                workInfo = periodicWorkInfo
            )

            // Phần 5: Bảng tổng kết các kiến thức đã hoàn thành
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Các khái niệm Bài học 12 đã triển khai:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    ConceptItem("Mô hình Repository tách biệt lớp Dữ liệu & UI")
                    ConceptItem("Tạo ViewModel thông qua Factory (ViewModelProvider.Factory)")
                    ConceptItem("Lập lịch công việc ngầm hiệu quả với WorkManager")
                    ConceptItem("Định nghĩa lớp CoroutineWorker (SyncWorker, PeriodicWorker)")
                    ConceptItem("Tạo và xếp hàng các yêu cầu công việc 1 lần và định kỳ")
                    ConceptItem("Truyền dữ liệu Input/Output & Thiết lập điều kiện (Constraints)")
                }
            }
        }
    }
}

@Composable
fun WorkStatusCard(
    title: String,
    workInfo: WorkInfo?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (workInfo == null) {
                Text(
                    text = "Chưa có nhiệm vụ nào được xếp hàng.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Trạng thái: ", fontWeight = FontWeight.SemiBold)
                    StatusBadge(state = workInfo.state)
                }

                if (workInfo.state == WorkInfo.State.RUNNING) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                // Trích xuất Dữ liệu Đầu ra (Output Data) khi công việc hoàn thành THÀNH CÔNG
                val resultText = workInfo.outputData.getString(WorkerConstants.KEY_OUTPUT_RESULT)
                val timestamp = workInfo.outputData.getString(WorkerConstants.KEY_OUTPUT_TIMESTAMP)
                val items = workInfo.outputData.getInt(WorkerConstants.KEY_OUTPUT_PROCESSED_ITEMS, -1)

                AnimatedVisibility(visible = !resultText.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        HorizontalDivider()
                        Text("Dữ liệu Đầu ra Nhận được:", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        resultText?.let { Text("• Kết quả: $it", fontSize = 14.sp) }
                        timestamp?.let { Text("• Hoàn thành lúc: $it", fontSize = 14.sp) }
                        if (items != -1) {
                            Text("• Số mục đã xử lý: $items", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBadge(state: WorkInfo.State) {
    val (backgroundColor, textColor, statusLabel) = when (state) {
        WorkInfo.State.ENQUEUED -> Triple(Color(0xFF1E88E5), Color.White, "ĐÃ VÀO HÀNG ĐỢI")
        WorkInfo.State.RUNNING -> Triple(Color(0xFFFB8C00), Color.White, "ĐANG CHẠY")
        WorkInfo.State.SUCCEEDED -> Triple(Color(0xFF43A047), Color.White, "THÀNH CÔNG")
        WorkInfo.State.FAILED -> Triple(Color(0xFFE53935), Color.White, "THẤT BẠI")
        WorkInfo.State.BLOCKED -> Triple(Color(0xFF8E24AA), Color.White, "BỊ KHÓA")
        WorkInfo.State.CANCELLED -> Triple(Color(0xFF757575), Color.White, "ĐÃ HỦY")
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = statusLabel,
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ConceptItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text("✓", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}
