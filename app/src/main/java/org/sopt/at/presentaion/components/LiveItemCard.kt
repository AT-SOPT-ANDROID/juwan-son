package org.sopt.at.presentaion.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.data.remote.model.LiveItem
import org.sopt.at.presentaion.components.ui.theme.ui.theme.ATSOPTANDROIDTheme

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