package org.sopt.at.presentaion.components

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LiveItemCard(imageRes: Int, titleText: String) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            Image(painter = painterResource(id = imageRes), contentDescription = null)
            Text(text = titleText)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview9() {
//    ATSOPTANDROIDTheme {
//        LiveItemCard()
//    }
//}