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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajudaqui.dicegames.ui.theme.DiceGamesTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

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
fun Dice(number: Int, modifier: Modifier) {

    Canvas(modifier) {
        drawRoundRect(
            Color.LightGray,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft = Offset(10f, 10f),
            size = size
        )
        bullet(number)
    }
}

fun DrawScope.bullet(number: Int) {
    when (number) {
        1 -> {
            center()
        }

        2 -> {
            dois_indo()
        }

        3 -> {
            dois_indo()
            center()
        }

        4 -> {
            dois_indo()
            dois_vindo()
        }

        5 -> {
            dois_indo()
            center()
            dois_vindo()
        }

        6 -> {
            dois_indo()
            cima_baixo()
            dois_vindo()
        }
    }

}

fun DrawScope.circle(offset: () -> Offset) {

    val radius = Dp(30f).value

    drawCircle(
        Color.Black, radius = radius,
        center = offset()
    )

}

fun DrawScope.center() {
    circle() {
        Offset(size.width / 2, size.height / 2)
    }
}

fun DrawScope.dois_indo() {
    circle() {
        Offset(size.width - Dp(60f).value, Dp(60f).value)
    }
    circle() {
        Offset(Dp(60f).value, size.width - Dp(60f).value)
    }

}

fun DrawScope.dois_vindo() {
    circle() {
        Offset(size.width - Dp(60f).value, size.width - Dp(60f).value)
    }
    circle() {
        Offset(Dp(60f).value, Dp(60f).value)
    }

}

fun DrawScope.cima_baixo() {
    circle() {
        Offset(size.width / 2, size.width - Dp(60f).value)
    }
    circle() {
        Offset(size.width / 2, Dp(60f).value)
    }

}

@Composable
fun App() {

    // mantem o valor da variavel enquanto a tela é renderizada
    var face_dice by remember { mutableStateOf(3) }
    var time by remember { mutableStateOf(0) }


    LaunchedEffect(key1 = time) {
        // Para ir diminuindo aos poucos
        if (time > 0) {
            delay((500 * (1.0f / time).toLong()))
            face_dice = Random.nextInt(1, 7)
            time -= 1
        }
    }

    // interesante...
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Dice(
            face_dice, modifier = Modifier
                .size(96.dp, 96.dp)
                .align(Alignment.Center)
        )

        if (face_dice == 6) {
            Dice(
                4, modifier = Modifier
                    .size(96.dp, 96.dp)
                    .align(Alignment.TopStart)
            )
        }
        Button(
            onClick = {
                face_dice = 6
            }, modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (100).dp)
        ) {

            if (time > 0) {
                Text(text = "$time")
            } else {
                Text(text = "Jogar")
            }

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