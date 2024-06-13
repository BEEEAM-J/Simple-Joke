package com.beeeam.feature.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.beeeam.feature.theme.mainColor

@Composable
fun JokeDropDownMenu(
    text: String,
    dropDownItemClick: (String) -> Unit,
) {
    DropdownMenuItem(
        text = {
            Text(
                color = mainColor,
                text = text,
                fontWeight = FontWeight.ExtraBold,
            )
        },
        onClick = { dropDownItemClick(text) }
    )
}