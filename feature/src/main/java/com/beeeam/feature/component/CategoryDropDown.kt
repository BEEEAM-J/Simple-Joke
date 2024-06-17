package com.beeeam.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.beeeam.domain.model.Category
import com.beeeam.feature.R
import com.beeeam.feature.theme.black
import com.beeeam.feature.theme.mainColor

@Composable
fun CategoryDropDown(
    dropDownList: Category = Category(),
    dropDownState: Boolean,
    dropDownClick: () -> Unit,
    dropDownDismiss: () -> Unit,
    dropDownItemClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(32.dp)
            .clickable { dropDownClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(12.dp),
            painter = painterResource(id = R.drawable.arrow_down),
            contentDescription = ""
        )
        Text(
            text = "Choose!",
            color = mainColor,
            fontWeight = FontWeight.Bold
        )
    }
    DropdownMenu(
        modifier = Modifier
            .height(200.dp)
            .background(black),
        expanded = dropDownState,
        onDismissRequest = dropDownDismiss,
        offset = DpOffset(150.dp, 0.dp)
    ) {
        dropDownList.category.forEach { category ->
            JokeDropDownMenu(category, dropDownItemClick)
        }
    }
}