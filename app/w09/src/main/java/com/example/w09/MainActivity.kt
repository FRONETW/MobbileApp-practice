package com.example.w09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w09.ui.theme.AndroidappTheme

val selfIntroList = listOf(
    "학력: 대전 우송대학교 컴퓨터공학과 재학",
    "경험: 앱 개발 동아리 활동, 프로젝트 진행 경험",
    "강점: 문제 해결 능력, 꾸준함, 새로운 기술 학습",
    "목표: Jetpack Compose 활용 앱 개발 능력 강화"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfIntroScreen(list: List<String>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("나의 자기소개서") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidappTheme {
                SelfIntroScreen(selfIntroList)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelfIntroPreview() {
    AndroidappTheme {
        SelfIntroScreen(selfIntroList)
    }
}
