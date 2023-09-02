package no.app.features.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun GenericErrorState(
    exception: String,
) {
    Box(
        modifier = Modifier.padding(32.dp).fillMaxSize().testTag("Error")
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Oh no! Something went wrong! See if you can find the error: $exception",
        )
    }
}
