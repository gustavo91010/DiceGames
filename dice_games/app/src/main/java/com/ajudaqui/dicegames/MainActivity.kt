package com.ajudaqui.dicegames

import android.R
import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
                    App(this)
                }
            }
        }
    }
}


@Composable
fun Dice(number: Int, modifier: Modifier) {

    Canvas(modifier) {
        drawRoundRect(
            Color.White,
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
fun App(context: Context) {

    // mantem o valor da variavel enquanto a tela é renderizada
    var face_dice by remember { mutableStateOf(1) }
    var time by remember { mutableStateOf(0) }
    var end_game by remember { mutableStateOf(false) } // Adiciona o estado para o fim do jogo


    LaunchedEffect(key1 = time) {
        // Para ir diminuindo aos poucos
        delay(500L)
        if (time > 0) {
            delay((500 * (1.0f / time).toLong()))
//            delay(1000L)
            face_dice = Random.nextInt(1, 7)
            time -= 1
        }else{
            end_game = true
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

//        if (face_dice == 6) {
//            Dice(
//                4, modifier = Modifier
//                    .size(96.dp, 96.dp)
//                    .align(Alignment.TopStart)
//            )
//        }
        Button(
            onClick = {
                time = 10
            }, modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (100).dp)
        ) {
            if (time == 0) {
                end_game = false // Reseta o fim do jogo ao reiniciar
                Text(text = "Iniciar")

            } else {
                Text(text = "acaba em: $time")
            }

            if(end_game){
                Toast.makeText(
                    context,
                    "Fim de jogo, seu resultado foi: $face_dice",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DiceGamesTheme {
//// esse surface esta reprsentando a tela do smartfone
//        Surface(modifier = Modifier.fillMaxSize()) {
//            App()
// para o previw poder fincionar, retira o contex do metodo app
//        }
//    }
//}