package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.robert.dailygratitude.ui.theme.Typography

@Composable
fun EntryDetailsTopAppBar(
    onBack: () -> Unit,
    onEditClick: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back arrow"
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            style = Typography.bodyLarge,
            text = "Back"
        )

        IconButton(
            onClick = onEditClick
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "edit button"
            )
        }

        IconButton(
            onClick = { showSnackbar("Clicked delete") }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete button"
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun EntryDetailsTopAppBarPreview() {
    EntryDetailsTopAppBar(
        onBack = {},
        onEditClick = {},
        showSnackbar = {}
    )
}