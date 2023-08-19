package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.robert.dailygratitude.ui.theme.Typography

@Composable
fun HomeTopAppBar(
    onAddClick: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            style = Typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            text = "Daily Gratitude"
        )

        IconButton(
            onClick = { showSnackbar("Clicked the star") }
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null
            )
        }

        IconButton(
            onClick = onAddClick
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add entry"
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomeTopAppBarPreview() {
    HomeTopAppBar(
        onAddClick = {},
        showSnackbar = {}
    )
}