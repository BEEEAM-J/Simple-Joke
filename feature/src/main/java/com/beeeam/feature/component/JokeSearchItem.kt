package com.beeeam.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beeeam.feature.R
import com.beeeam.feature.theme.mainColor

@Composable
fun JokeSearchItem(category: String, joke: String, date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
    ) {
        Image(
            modifier = Modifier
                .size(72.dp)
                .padding(16.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_homer_simpson),
            contentDescription = "Joke Icon"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp),
            text = "$joke",
            color = mainColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = "$date",
            color = mainColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
fun JokeSearchItemPreview() {
    JokeSearchItem(
        category = "category",
        joke = "Joke Joke Joke Joke Joke Joke Joke ",
        date = "2020-01-01"
    )
}