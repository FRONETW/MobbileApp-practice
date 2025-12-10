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
import androidx.compose.material.icons.filled.Menu
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
fun SelfIntroTopBar(
    title: String,
    recommendCount: Int,
    popularCount: Int,
    onMenuClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                androidx.compose.foundation.layout.Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(text = "추천: $recommendCount")
                    Text(text = "인기: $popularCount")
                }
            }
        },
        navigationIcon = {
            onMenuClick?.let {
                androidx.compose.material3.IconButton(onClick = it) {
                    androidx.compose.material3.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Menu,
                        contentDescription = "메뉴"
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfIntroScreen(list: List<String>, recommendCount: Int, popularCount: Int) {
    Scaffold(
        topBar = {
            SelfIntroTopBar(
                title = "나의 자기소개서",
                recommendCount = recommendCount,
                popularCount = popularCount,
                onMenuClick = { /* 메뉴 클릭 처리 */ }
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
                SelfIntroScreen(
                    list = selfIntroList,
                    recommendCount = 123,   // 예시 값
                    popularCount = 456)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelfIntroPreview() {
    AndroidappTheme {
        SelfIntroScreen(
            list = selfIntroList,
            recommendCount = 123,   // 예시 값
            popularCount = 456 )
    }
}
