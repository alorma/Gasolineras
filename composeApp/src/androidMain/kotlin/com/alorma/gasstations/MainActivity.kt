package com.alorma.gasstations

import com.alorma.gasstations.ui.App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.domain.GasStationsSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val databaseDriverFactory = DatabaseDriverFactory(LocalContext.current)
            val sdk = GasStationsSdk(databaseDriverFactory)
            App(sdk)
        }
    }
}