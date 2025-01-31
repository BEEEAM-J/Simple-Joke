package com.beeeam.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beeeam.feature.R
import com.beeeam.feature.theme.mainColor

@Composable
fun JokeSearchBar(
    modifier: Modifier,
    value: String = "",
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    onClickSearchBar: () -> Unit,
    onValueChanged: (String) -> Unit,
    onEnterClicked: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isFocused) {
        if(isFocused) {
            onClickSearchBar()
        }
    }

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)         // 포커스 요청자 연결
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused    // 포커스 상태 업데이트
            },
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = mainColor),
        cursorBrush = SolidColor(Color.White),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (!value.isNullOrEmpty()) {
                    onEnterClicked(value)
                    keyboardController?.hide()
                }
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(size = 10.dp))
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = mainColor,
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
                {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = "Input!",
                            color = mainColor,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        },
    )
}

@Preview(apiLevel = 33)
@Composable
fun SearchBarPreview() {
//    JokeSearchBar(
//        modifier = Modifier,
//        value = "",
//        onValueChanged = {},
//        onEnterClicked = {},
//    )
}