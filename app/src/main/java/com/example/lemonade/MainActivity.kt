package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)

) {

    var currentStep by remember { mutableStateOf(1) }
    var contador by remember { mutableStateOf(0) }
    var clicsRequeridos by remember { mutableStateOf(0) }

    val imagenes = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    val textos = listOf(
        R.string.Lemon_Tree,
        R.string.Lemon,
        R.string.Glass_Of_Lemonade,
        R.string.Empty_Glass
    )

    if (currentStep == 2 && clicsRequeridos == 0) {
        clicsRequeridos = (2..4).random()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF_EFEB44))
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    when (currentStep) {
        in 1..4 -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(imagenes[currentStep - 1]),
                    contentDescription = stringResource(R.string.Lemon_Tree),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            if (currentStep == 2) {
                                if (contador == clicsRequeridos - 1) {
                                    currentStep = if (currentStep < 4) currentStep + 1 else 1
                                    clicsRequeridos = if (currentStep == 2) (2..4).random() else 0
                                    contador = 0
                                } else {
                                    contador++
                                }
                            } else {
                                currentStep = if (currentStep < 4) currentStep + 1 else 1
                            }
                        }
                        .background(
                            Color(red = 85, green = 247, blue = 215),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(red = 85, green = 247, blue = 215),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(textos[currentStep - 1]),
                    fontSize = 18.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

