package cl.antol.testapp.model

data class Question(
    val text: String,
    val options: List<Option>
)

data class Option(
    val text: String,
    val points: Int
)

