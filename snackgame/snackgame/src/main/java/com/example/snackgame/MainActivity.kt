package com.example.snackgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeGame()
        }
    }
}

enum class Direction { UP, DOWN, LEFT, RIGHT }

@Composable
fun SnakeGame() {
    var snake by remember { mutableStateOf(listOf(Pair(5, 5))) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var food by remember { mutableStateOf(Pair(10, 10)) }
    var gameOver by remember { mutableStateOf(false) }

    val gridSize = 20

    LaunchedEffect(snake, direction) {
        if (!gameOver) {
            delay(200L)
            val head = snake.first()
            val newHead = when (direction) {
                Direction.UP -> Pair(head.first, head.second - 1)
                Direction.DOWN -> Pair(head.first, head.second + 1)
                Direction.LEFT -> Pair(head.first - 1, head.second)
                Direction.RIGHT -> Pair(head.first + 1, head.second)
            }

            // 충돌 검사
            if (newHead.first < 0 || newHead.second < 0 ||
                newHead.first >= gridSize || newHead.second >= gridSize ||
                newHead in snake
            ) {
                gameOver = true
            } else {
                snake = listOf(newHead) + snake
                if (newHead == food) {
                    food = Pair(Random.nextInt(gridSize), Random.nextInt(gridSize))
                } else {
                    snake = snake.dropLast(1)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (gameOver) {
            Text(
                text = "💀 Game Over 💀",
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // 게임 화면
        Canvas(
            modifier = Modifier
                .size(300.dp)
                .padding(8.dp)
                .background(Color.DarkGray)
        ) {
            val cellSize = size.width / gridSize

            // 음식
            drawRect(
                color = Color.Red,
                topLeft = androidx.compose.ui.geometry.Offset(
                    food.first * cellSize,
                    food.second * cellSize
                ),
                size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
            )

            // 뱀
            snake.forEach {
                drawRect(
                    color = Color.Green,
                    topLeft = androidx.compose.ui.geometry.Offset(
                        it.first * cellSize,
                        it.second * cellSize
                    ),
                    size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
                )
            }
        }

        // 조작 버튼
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { if (direction != Direction.DOWN) direction = Direction.UP }) {
                Text("⬆️")
            }
            Row {
                Button(onClick = { if (direction != Direction.RIGHT) direction = Direction.LEFT }) {
                    Text("⬅️")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { if (direction != Direction.LEFT) direction = Direction.RIGHT }) {
                    Text("➡️")
                }
            }
            Button(onClick = { if (direction != Direction.UP) direction = Direction.DOWN }) {
                Text("⬇️")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (gameOver) {
            Button(onClick = {
                snake = listOf(Pair(5, 5))
                direction = Direction.RIGHT
                food = Pair(10, 10)
                gameOver = false
            }) {
                Text("🔄 다시 시작")
            }
        }
    }
}
