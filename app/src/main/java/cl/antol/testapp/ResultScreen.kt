package cl.antol.testapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.antol.testapp.model.Option

data class ResultInfo(
    val title: String,
    val emoji: ImageVector,
    val message: String,
    val color: Color
)

@Composable
fun ResultScreen(
    answers: List<Option>,
    onRestart: () -> Unit
) {
    val totalPoints = answers.sumOf { it.points }

    val resultInfo = when (totalPoints) {
        in 0..5 -> ResultInfo(
            "🤓 Más normal no se puede",
            Icons.Default.EmojiEvents,
            "Eres una persona completamente racional, organizada y segura. Puede que seas el/la único/a que mantiene la cordura en cualquier situación. ¡Felicidades!\n\n🎁 Bonus:\nEres el/la encargado/a de traer orden al caos. Siempre confiable, aunque a veces demasiado serio/a. ¡No temas soltar la risa!",
            Color(0xFF4CAF50) // verde
        )
        in 6..12 -> ResultInfo(
            "😄 Un poco fuera de serie",
            Icons.Default.EmojiEvents,
            "Tienes un toque especial, eres creativo/a y te gusta salirte un poco de lo común. Diviertes a quienes están contigo, pero sabes cuándo frenar.\n\n🎁 Bonus:\nTienes un pie en la realidad y otro en el universo paralelo. Equilibras lo raro y lo normal perfectamente. ¡Eres el/la centro de atención moderada!",
            Color(0xFFFFC107) // amarillo
        )
        in 13..20 -> ResultInfo(
            "🤪 Raro/a de libro",
            Icons.Default.EmojiEvents,
            "¡Eres pura rareza! Tu mente es un mundo lleno de ideas locas, bromas internas y obsesiones únicas. La gente a veces no sabe qué pensar de ti, pero eso te hace genial.\n\n🎁 Bonus:\nVives en tu propio mundo y lo aceptas con orgullo. Si hubiera un premio a la originalidad, tú lo ganarías. ¡Sigue siendo tú mism@!",
            Color(0xFFF44336) // rojo
        )
        else -> ResultInfo(
            "Resultado desconocido",
            Icons.Default.EmojiEvents,
            "",
            Color.Gray
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = resultInfo.emoji,
                contentDescription = null,
                tint = resultInfo.color,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = resultInfo.title,
                style = MaterialTheme.typography.headlineMedium,
                color = resultInfo.color,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "🔢 Puntaje total: $totalPoints puntos",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(resultInfo.color.copy(alpha = 0.1f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = resultInfo.message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRestart,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = resultInfo.color)
            ) {
                Text(text = "Reiniciar Test", color = Color.White)
            }
        }
    }
}
