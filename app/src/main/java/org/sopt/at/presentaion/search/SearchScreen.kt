package org.sopt.at.presentaion.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.theme.TivingTheme

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }
    val nicknameList by searchViewModel.nicknameList.observeAsState(emptyList())
    val errorMessage by searchViewModel.errorMessage.observeAsState("")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(50.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                contentColorFor(backgroundColor = Color.LightGray),
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.LightGray,
                disabledTextColor = Color.Gray,
                disabledLabelColor = Color.Gray
            )
        )

        Button(
            onClick = {
                if (text.isNotEmpty()) {

                    searchViewModel.searchNickname(text)
                }
            },
            colors = ButtonColors(
                contentColor = TivingTheme.colors.Red200,
                disabledContentColor = TivingTheme.colors.Red200,
                disabledContainerColor = TivingTheme.colors.Red200,
                containerColor = TivingTheme.colors.Red200
            )

        ) {
            Text("검색", color = Color.White)
        }

        if (nicknameList.isNotEmpty()) {
            Column {
                nicknameList.forEach { nickname ->
                    Text(
                        text = nickname,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}