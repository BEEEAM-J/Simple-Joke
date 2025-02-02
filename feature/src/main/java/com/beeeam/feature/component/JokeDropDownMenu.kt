package com.beeeam.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beeeam.feature.theme.mainColor
import com.beeeam.feature.theme.white

@Composable
fun JokeDropDownMenu(
    text: String,
    dropDownItemClick: (String) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(white)
            .clickable { dropDownItemClick(text) }
            .padding(12.dp),
        text = text,
        color = mainColor,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight(600)
    )
}