import androidx.compose.ui.window.ComposeUIViewController
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.ui.App
import com.russhwolf.settings.Settings

fun MainViewController() = ComposeUIViewController {
  val databaseDriverFactory = DatabaseDriverFactory()
  val sdk = GasStationsSdk(databaseDriverFactory, Settings())

  App(sdk)
}
