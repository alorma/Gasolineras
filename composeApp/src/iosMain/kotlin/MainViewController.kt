import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
fun MainViewController() = ComposeUIViewController {
  MaterialTheme(
    colorScheme = darkColorScheme(),
  ) {
    Scaffold(
      topBar = { TopAppBar(title = { Text(text = "Desktop demp") }) },
    ) { padding ->
      ThemeViewer(
        modifier = Modifier
          .consumeWindowInsets(padding)
          .padding(top = padding.calculateTopPadding())
          .fillMaxSize(),
      )
    }
  }
}
