package com.beeeam.simplejoke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beeeam.simplejoke.ui.theme.SimpleJokeTheme
import com.beeeam.simplejoke.ui.theme.black
import com.beeeam.simplejoke.ui.theme.mainColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleJokeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var title by remember { mutableStateOf("Simple Joke!") }
    val dropDownItemClick = { text: String -> title = text }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            modifier = Modifier.padding(42.dp),
            painter = painterResource(id = R.drawable.homer_simpson),
            contentDescription = ""
        )
        Text(
            text = title,
            color = mainColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
        )
        CategoryDropDown(
            dropDownState = isDropDownMenuExpanded,
            dropDownClick = {
                isDropDownMenuExpanded = !isDropDownMenuExpanded
            },
            dropDownItemClick = dropDownItemClick,
        )
    }
}

@Composable
fun CategoryDropDown(
    dropDownState: Boolean,
    dropDownClick: () -> Unit,
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
            .wrapContentSize()
            .background(black),
        expanded = dropDownState,
        onDismissRequest = dropDownClick,
        offset = DpOffset(150.dp, 0.dp)
    ) {
        JokeDropDownMenu("1", dropDownItemClick)
        JokeDropDownMenu("2", dropDownItemClick)
        JokeDropDownMenu("3", dropDownItemClick)
    }
}

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

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    SimpleJokeTheme {
        MainScreen()
    }
}