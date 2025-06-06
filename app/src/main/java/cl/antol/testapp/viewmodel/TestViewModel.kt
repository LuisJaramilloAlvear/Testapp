package cl.antol.testapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cl.antol.testapp.model.Option
import cl.antol.testapp.model.Question

enum class ScreenState {
    TEST,
    RESULT
}

class TestViewModel : ViewModel() {

    private val questions = listOf(
        Question(
            text = "¿Alguna vez has hablado con tu comida antes de comértela?",
            options = listOf(
                Option("Sí, le doy las gracias.", 1),
                Option("Solo cuando estoy muy hambriento/a.", 2),
                Option("Nunca.", 0)
            )
        ),
        Question(
            text = "¿Cuál es tu respuesta cuando alguien dice “Saludos desde el más allá”?",
            options = listOf(
                Option("Me río y lo sigo jugando.", 2),
                Option("Le pregunto si está bien.", 1),
                Option("Lo ignoro o no entiendo la broma.", 0)
            )
        ),
        Question(
            text = "¿Usas GIFs o memes como forma principal de comunicación?",
            options = listOf(
                Option("Totalmente, ni hablo normal ya.", 2),
                Option("A veces, depende con quién esté.", 1),
                Option("No, prefiero escribir.", 0)
            )
        ),
        Question(
            text = "¿Has usado una voz diferente al pedir algo en un restaurante?",
            options = listOf(
                Option("Sí, por pura diversión.", 2),
                Option("Por error, una vez.", 1),
                Option("Nunca.", 0)
            )
        ),
        Question(
            text = "¿Crees que los animales pueden hablar pero solo lo hacen cuando no estamos cerca?",
            options = listOf(
                Option("Totalmente, mi perro me entiende todo.", 2),
                Option("Es posible…", 1),
                Option("No, eso es ilógico.", 0)
            )
        ),
        Question(
            text = "¿Te has reído solo/a sin motivo aparente en público?",
            options = listOf(
                Option("Sí, varias veces.", 2),
                Option("Una vez, por un recuerdo chistoso.", 1),
                Option("Nunca.", 0)
            )
        ),
        Question(
            text = "¿Tienes una colección extraña (ej: etiquetas de frutas, tapas de botella, etc.)?",
            options = listOf(
                Option("Sí, tengo varias.", 2),
                Option("Tengo una cosa pequeña, pero no es gran cosa.", 1),
                Option("No colecciono nada raro.", 0)
            )
        ),
        Question(
            text = "¿Haces cosas normales de forma extraña (ej: cepillarte los dientes bailando)?",
            options = listOf(
                Option("Todo lo hago con estilo raro.", 2),
                Option("A veces, cuando nadie me ve.", 1),
                Option("No, trato de ser normal.", 0)
            )
        ),
        Question(
            text = "¿Alguna vez has hecho una pregunta filosófica a un amigo sin motivo?",
            options = listOf(
                Option("Sí, como “¿Y si todos somos sueños de otra persona?”", 2),
                Option("Solo en momentos raros.", 1),
                Option("Nunca.", 0)
            )
        ),
        Question(
            text = "¿Prefieres cenar con un extraterrestre o con un grupo de adultos normales?",
            options = listOf(
                Option("Con un extraterrestre, suena más interesante.", 2),
                Option("Depende del extraterrestre.", 1),
                Option("Con personas normales.", 0)
            )
        )
    )

    val currentQuestionIndex: MutableState<Int> = mutableStateOf(0)
    val screenState: MutableState<ScreenState> = mutableStateOf(ScreenState.TEST)
    val selectedAnswers = mutableListOf<Option>()

    val currentQuestion: Question
        get() = questions[currentQuestionIndex.value]

    fun selectAnswer(option: Option) {
        selectedAnswers.add(option)

        if (currentQuestionIndex.value < questions.size - 1) {
            currentQuestionIndex.value += 1
        } else {
            screenState.value = ScreenState.RESULT
        }
    }

    fun resetTest() {
        selectedAnswers.clear()
        currentQuestionIndex.value = 0
        screenState.value = ScreenState.TEST
    }

    fun getTotalPoints(): Int {
        return selectedAnswers.sumOf { it.points }
    }
}
