package com.example.w05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.w05.ui.theme.AndroidappTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidappTheme {
                // TODO: 여기에 카운터와 스톱워치 UI를 만들도록 안내
                val count = remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CounterApp(count)
                    Spacer(modifier = Modifier.height(32.dp))
                    StopWatchApp()
                }
            }
        }
    }
}


@Composable
fun CounterApp() {
    // TODO: 상태 변수 정의
    // TODO: 버튼 클릭 시 상태 변경 로직 작성
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: 0",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ) // TODO: 상태값 표시
        Row {
            Button(onClick = { /* TODO: 카운터 증가 로직 */ }) {
                Text("Increase")
            }
            Button(onClick = { /* TODO: 카운터 증가 로직 */ }) {
                Text("Reset")
            }
        }
    }
}


@Composable
fun StopWatchApp() {
    // 1. 시간(밀리초)과 타이머 실행 여부를 기억할 State 변수 추가
    var timeInMillis by remember { mutableStateOf(1L) }
    var isRunning by remember { mutableStateOf(false) }
    fun formatTime(timeInMillis: Long): String {
        val seconds = (timeInMillis / 1000) % 60
        val minutes = (timeInMillis / 1000) / 60
        val millis = (timeInMillis % 1000) / 10
        return String.format("%02d:%02d.%02d", minutes, seconds, millis)
    }

    // 1. isRunning 상태가 true일 때만 실행되는 LaunchedEffect 추가
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                delay(10L) // 10밀리초마다
                timeInMillis += 10L // 시간을 10밀리초씩 증가
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = formatTime(timeInMillis), // 2. State 변수를 사용해 시간 표시
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            // 3. 버튼 클릭 시 isRunning 상태를 변경
            Button(onClick = { isRunning = true }) { Text("Start") }
            Button(onClick = { isRunning = false }) { Text("Stop") }
            Button(onClick = {
                isRunning = false
                timeInMillis = 0L
            }) { Text("Reset") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    CounterApp()
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatchApp()
}
@Composable
fun CounterApp(count: MutableState<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ) // TODO: 상태값 표시
        Row {
            Button(onClick = { count.value++ }) { Text("Increase") }
            Button(onClick = { count.value = 0 }) { Text("Reset") }
        }
    }
}



