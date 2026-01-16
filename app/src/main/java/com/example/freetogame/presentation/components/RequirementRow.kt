package com.example.freetogame.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequirementRow(label: String, value: String?) {
    if (!value.isNullOrEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontSize = 15.sp
            )
            Text(
                text = value,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
    }
}
