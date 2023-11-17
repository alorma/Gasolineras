package com.alorma.gasstations.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun dynamicColorScheme(useDarkTheme: Boolean): ColorScheme?