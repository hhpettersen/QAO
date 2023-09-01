package no.app.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabeledText(
    label: String,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = textStyle,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = text,
            style = textStyle,
        )
    }
}