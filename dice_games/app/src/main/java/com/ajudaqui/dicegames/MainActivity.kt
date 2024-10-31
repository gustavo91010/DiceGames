package com.ajudaqui.dicegames

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajudaqui.dicegames.ui.theme.DiceGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    // interesante...
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Canvas(modifier = Modifier.size(96.dp, 96.dp).align(Alignment.Center)) {
            drawRoundRect(
                Color.Green,
                cornerRadius =  CornerRadius(20f, 20f),
                topLeft = Offset(10f, 10f),
                size= size
            )

            drawCircle(
                Color.Black, radius= Dp(20f).value,
                center= Offset(size.width/2, size.height/2)
            )
        }

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (100).dp)
        ) {
            Text(text = "Jogar")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceGamesTheme {
// esse surface esta reprsentando a tela do smartfone
        Surface(modifier = Modifier.fillMaxSize()) {
            App()
        }
    }
}