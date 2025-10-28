package com.example.snakegame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class Direction { UP, DOWN, LEFT, RIGHT }

@Composable
fun SnakeGameScreen() {
    val numRows = 20
    val numCols = 20
    val initialSnake = listOf(Pair(10, 10))
    var snake by remember { mutableStateOf(initialSnake) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var food by remember { mutableStateOf(Pair(Random.nextInt(numCols), Random.nextInt(numRows))) }
    var score by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }

    // 🐍 게임 루프
    LaunchedEffect(Unit) {
        while (!gameOver) {
            delay(300L) // 속도 조절
            val head = snake.first()
            val newHead = when(direction) {
                Direction.UP -> Pair(head.first, head.second - 1)
                Direction.DOWN -> Pair(head.first, head.second + 1)
                Direction.LEFT -> Pair(head.first - 1, head.second)
                Direction.RIGHT -> Pair(head.first + 1, head.second)
            }

            // 충돌 체크
            if (newHead.first < 0 || newHead.first >= numCols ||
                newHead.second < 0 || newHead.second >= numRows ||
                snake.contains(newHead)
            ) {
                gameOver = true
                break
            }

            val newSnake = listOf(newHead) + snake
            if (newHead == food) {
                score += 1
                food = Pair(Random.nextInt(numCols), Random.nextInt(numRows))
            } else {
                snake = newSnake.dropLast(1)
            }
            snake = newSnake.take(newSnake.size)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Score: $score", color = Color.Black)

        // 게임판
        Column {
            for (y in 0 until numRows) {
                Row {
                    for (x in 0 until numCols) {
                        val color = when {
                            snake.contains(Pair(x, y)) -> Color.Green
                            food == Pair(x, y) -> Color.Red
                            else -> Color.LightGray
                        }
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(color)
                                .clickable {
                                    // 간단히 클릭으로 방향 변경
                                    direction = when(direction) {
                                        Direction.UP -> Direction.RIGHT
                                        Direction.RIGHT -> Direction.DOWN
                                        Direction.DOWN -> Direction.LEFT
                                        Direction.LEFT -> Direction.UP
                                    }
                                }
                        )
                    }
                }
            }
        }

        if (gameOver) {
            Text("Game Over!", color = Color.Red)
        }
    }
}
