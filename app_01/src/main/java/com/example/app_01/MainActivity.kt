package com.example.app_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_01.ui.theme.AndroidappTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidappTheme {
                MainScreen()
            }
        }
    }
}

data class Message(val name: String, val msg:String)
data class Profile(val name: String, val msg:String)

@Composable
fun MainScreen(){
    Surface{
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            
        }
    }
}

@Composable
fun Message

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    MainScreen()
}