package com.beeeam.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val dropdownIcon = if(dropDownState) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
    Row(
        modifier = Modifier
            .padding(32.dp)
            .clickable { dropDownClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp),
            painter = painterResource(id = dropdownIcon),
            contentDescription = ""
        )
        Text(
            text = "Choose!",
            color = mainColor,
            fontSize = 20.sp,
            fontWeight = FontWeight(600)
        )
    }
    DropdownMenu(
        modifier = Modifier
            .height(200.dp)
            .background(black),
        expanded = dropDownState,
        onDismissRequest = dropDownDismiss,
        offset = DpOffset(0.dp, 8.dp)
    ) {
        dropDownList.category.forEach { category ->
            JokeDropDownMenu(category, dropDownItemClick)
        }
    }
}