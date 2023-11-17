import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        HelperKt.doInitKoin()
        HelperKt.debugBuild()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}