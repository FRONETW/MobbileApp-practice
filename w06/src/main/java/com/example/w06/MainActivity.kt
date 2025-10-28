package com.example.w06

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w06.ui.theme.AndroidappTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

// --- 데이터 클래스 ---
data class Bubble(
    val id: Int,
    var position: Offset,
    val radius: Float,
    val color: Color,
    val creationTime: Long = System.currentTimeMillis(),
    val velocityX: Float = Random.nextFloat() * 4 - 2,
    val velocityY: Float = Random.nextFloat() * 4 - 2
)

// --- 게임 상태 클래스 ---
class GameState(initialBubbles: List<Bubble> = emptyList()) {
    var bubbles by mutableStateOf(initialBubbles)
    var score by mutableStateOf(0)
    var isGameOver by mutableStateOf(false)
    var timeLeft by mutableStateOf(60)
}

// --- MainActivity ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BubbleGameScreen()
                }
            }
        }
    }
}

// --- 메인 게임 화면 ---
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BubbleGameScreen() {
    val gameState: GameState = remember {
        GameState(
            initialBubbles = listOf(
                Bubble(
                    id = Random.nextInt(),
                    position = Offset(
                        x = Random.nextFloat() * 150,
                        y = Random.nextFloat() * 150
                    ),
                    radius = Random.nextFloat() * 50,
                    color = Color(
                        red = Random.nextInt(256),
                        green = Random.nextInt(256),
                        blue = Random.nextInt(256),
                        alpha = 200
                    )
                )
            )
        )
    }

    val density = LocalDensity.current

    // --- 타이머 ---
    LaunchedEffect(gameState.isGameOver) {
        while (!gameState.isGameOver && gameState.timeLeft > 0) {
            delay(1000)
            gameState.timeLeft--
            if (gameState.timeLeft <= 0) gameState.isGameOver = true
            val now = System.currentTimeMillis()
            gameState.bubbles = gameState.bubbles.filter { now - it.creationTime < 3000 }
        }
    }

    // --- UI ---
    Column(modifier = Modifier.fillMaxSize()) {
        GameStatusRow(score = gameState.score, timeLeft = gameState.timeLeft)

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val canvasWidthPx = with(density) { maxWidth.toPx() }
            val canvasHeightPx = with(density) { maxHeight.toPx() }

            // 1️⃣ 화면에 버블 표시
            gameState.bubbles.forEach { bubble ->
                BubbleComposable(bubble = bubble) {
                    gameState.bubbles = gameState.bubbles.filter { it.id != bubble.id }
                    gameState.score += 1
                }
            }

            // 2️⃣ 버블 움직임 및 랜덤 생성
            LaunchedEffect(gameState.isGameOver) {
                while (!gameState.isGameOver) {
                    delay(16)
                    // 버블 이동
                        gameState.bubbles = gameState.bubbles.map { bubble ->
                        with(density) {
                            val radiusPx = bubble.radius.dp.toPx()
                            var xPx = bubble.position.x.dp.toPx() + bubble.velocityX.dp.toPx()
                            var yPx = bubble.position.y.dp.toPx() + bubble.velocityY.dp.toPx()
                            var vx = bubble.velocityX
                            var vy = bubble.velocityY

                            if (xPx < radiusPx || xPx > canvasWidthPx - radiusPx) vx *= -1
                            if (yPx < radiusPx || yPx > canvasHeightPx - radiusPx) vy *= -1

                            xPx = xPx.coerceIn(radiusPx, canvasWidthPx - radiusPx)
                            yPx = yPx.coerceIn(radiusPx, canvasHeightPx - radiusPx)

                            bubble.copy(
                                position = Offset(xPx.toDp().value, yPx.toDp().value),
                                velocityX = vx,
                                velocityY = vy
                            )
                        }
                    }

                    // 랜덤으로 새로운 버블 추가 (최대 15개)
                    if (Random.nextFloat() < 0.05f && gameState.bubbles.size < 15) {
                        val newBubble = Bubble(
                            id = Random.nextInt(),
                            position = Offset(
                                x = Random.nextFloat() * maxWidth.value,
                                y = Random.nextFloat() * maxHeight.value
                            ),
                            radius = Random.nextFloat() * 50 + 50,
                            color = Color(
                                red = Random.nextInt(256),
                                green = Random.nextInt(256),
                                blue = Random.nextInt(256),
                                alpha = 200
                            )
                        )
                        gameState.bubbles = gameState.bubbles + newBubble
                    }

                    // 기본 최소 버블 3개 보장
                    if (gameState.bubbles.isEmpty()) {
                        val newBubbles = List(3) {
                            Bubble(
                                id = Random.nextInt(),
                                position = Offset(
                                    x = Random.nextFloat() * maxWidth.value,
                                    y = Random.nextFloat() * maxHeight.value
                                ),
                                radius = Random.nextFloat() * 25 + 25,
                                color = Color(
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    200
                                )
                            )
                        }
                        gameState.bubbles = newBubbles
                    }
                }
            }

            // 3️⃣ 게임 오버 다이얼로그
            if (gameState.isGameOver) {
                GameOverDialog(
                    score = gameState.score,
                    onRestart = {
                        gameState.bubbles = listOf()
                        gameState.score = 0
                        gameState.timeLeft = 60
                        gameState.isGameOver = false
                    },
                    onExit = {
                        // MainActivity 종료
                        // 여기서는 필요에 따라 finish() 또는 navBack 처리
                    }
                )
            }
        }
    }
}

// --- 상태 바 ---
@Composable
fun GameStatusRow(score: Int, timeLeft: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Score: $score", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Time: ${timeLeft}s", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

// --- 버블 UI ---
@Composable
fun BubbleComposable(bubble: Bubble, onClick: () -> Unit) {
    Canvas(
        modifier = Modifier
            .size((bubble.radius * 2).dp)
            .offset(x = bubble.position.x.dp, y = bubble.position.y.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        drawCircle(
            color = bubble.color,
            radius = bubble.radius,
            center = center
        )
    }
}

// --- 게임 오버 다이얼로그 ---
@Composable
fun GameOverDialog(score: Int, onRestart: () -> Unit, onExit: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("게임 오버") },
        text = { Text("당신의 점수는 $score 점입니다.") },
        confirmButton = {
            TextButton(onClick = onRestart) {
                Text("다시 시작")
            }
        },
        dismissButton = {
            TextButton(onClick = onExit) {
                Text("종료")
            }
        }
    )
}

// --- Preview ---
@Preview(showBackground = true)
@Composable
fun BubbleGamePreview() {
    AndroidappTheme {
        BubbleGameScreen()
    }
}