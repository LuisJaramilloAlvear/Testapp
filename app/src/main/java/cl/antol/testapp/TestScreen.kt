package cl.antol.testapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.antol.testapp.R
import cl.antol.testapp.viewmodel.TestViewModel

@Composable
fun TestScreen(viewModel: TestViewModel) {
    val question = viewModel.currentQuestion
    val questionIndex = viewModel.currentQuestionIndex.value

    val imageResId = when (questionIndex + 1) {
        1 -> R.drawable.img_1
        2 -> R.drawable.img_2
        3 -> R.drawable.img_3
        4 -> R.drawable.img_4
        5 -> R.drawable.img_5
        6 -> R.drawable.img_6
        7 -> R.drawable.img_7
        8 -> R.drawable.img_8
        9 -> R.drawable.img_9
        10 -> R.drawable.img_10
        else -> null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header con fondo pastel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0F7FA))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_kitsutest),
                    contentDescription = "Logo KitsuTest",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Test: ¿Qué tan raro/a eres?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            // Scroll completo para pregunta + opciones + imagen
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 12.dp) // Solo padding vertical aquí
            ) {
                // Contenido con padding horizontal
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Pregunta ${questionIndex + 1}:",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Text(
                        text = question.text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    question.options.forEach { option ->
                        Button(
                            onClick = { viewModel.selectAnswer(option) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = option.text)
                        }
                    }
                }

                // Imagen que ocupa todo el ancho (fuera del Column con padding)
                if (imageResId != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = "Imagen de la pregunta",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp)) // Separación del footer
                }
            }
        }

        // Footer fijo abajo con fondo pastel y padding
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xFFE0F7FA))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Creado con cariño por Luis • KitsuTest",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}