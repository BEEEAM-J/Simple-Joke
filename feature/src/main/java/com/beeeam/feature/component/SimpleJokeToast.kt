package com.beeeam.feature.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleJokeToast(
    visible: Boolean = false,
    msg: String = ""
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            SimpleJokeToastContent(
                modifier = Modifier.imePadding(),
                msg = msg,
            )
        }
    }
}

@Composable
fun SimpleJokeToastContent(
    modifier: Modifier = Modifier,
    msg: String = ""
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(25.dp),
            )
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = msg,
            color = Color.White,
        )
    }
}

@Preview(apiLevel = 33)
@Composable
fun SimpleJokeToastPreview() {
    SimpleJokeToastContent(
        msg = "text",
    )
}