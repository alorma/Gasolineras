import com.alorma.gasstations.di.PlatformModule
import com.alorma.gasstations.di.appModules
import org.koin.core.context.startKoin

fun initKoin() {
  startKoin {
    modules(appModules)
    modules(PlatformModule())
  }
}