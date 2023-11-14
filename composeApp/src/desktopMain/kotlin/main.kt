import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalMaterial3Api::class) fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    MaterialTheme {
      PermanentNavigationDrawer(
        modifier = Modifier
          .fillMaxHeight()
          .widthIn(300.dp),
        drawerContent = {
          ThemeViewer(
            Modifier
              .fillMaxHeight()
              .width(300.dp)
          )
        },
      ) {
        Scaffold(
          topBar = { TopAppBar(title = { Text(text = "Desktop demp") }) },
        ) {

        }
      }
    }
  }
}