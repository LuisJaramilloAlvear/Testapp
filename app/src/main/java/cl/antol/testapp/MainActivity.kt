package cl.antol.testapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.antol.testapp.ui.ResultScreen
import cl.antol.testapp.ui.TestScreen
import cl.antol.testapp.viewmodel.ScreenState
import cl.antol.testapp.viewmodel.TestViewModel
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val viewModel: TestViewModel = viewModel()

                when (viewModel.screenState.value) {
                    ScreenState.TEST -> TestScreen(viewModel)
                    ScreenState.RESULT -> ResultScreen(
                        answers = viewModel.selectedAnswers,
                        onRestart = { viewModel.resetTest() }
                    )
                }
            }
        }
    }
}
