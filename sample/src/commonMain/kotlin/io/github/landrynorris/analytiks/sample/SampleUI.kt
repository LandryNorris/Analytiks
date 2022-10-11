package io.github.landrynorris.analytiks.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SampleUI(logic: SampleComponent) {
    LaunchedEffect(Unit) {
        logic.onLaunch()
    }

    Column {
        Button(onClick = logic::button1Clicked) { Text("Button") }
    }
}
