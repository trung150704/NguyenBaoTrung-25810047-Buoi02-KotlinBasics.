package com.example.d8
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect

private const val TAG = "LifecycleTest"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() - Activity được khởi tạo")

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() - Activity bắt đầu hiển thị")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() - Activity sẵn sàng tương tác")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() - Activity tạm dừng (mất focus)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() - Activity bị che khuất hoàn toàn")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() - Activity bị hủy hoàn toàn")
    }
}

@Composable
fun MainScreen() {
    var normalCount by remember { mutableStateOf(0) }

    var savedCount by rememberSaveable { mutableStateOf(0) }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        Log.i(TAG, "LifecycleEventEffect: Màn hình Compose được hiển thị lại")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Thử nghiệm Configuration Change",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { normalCount++ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("remember (bị reset khi xoay): $normalCount")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { savedCount++ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("rememberSaveable (giữ dữ liệu): $savedCount")
        }
    }
}