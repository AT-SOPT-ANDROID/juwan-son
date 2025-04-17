package org.sopt.at.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.data.remote.model.BottomNavItem

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    BottomAppBar(containerColor = Color.Black) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.forEach { item ->
                val isSelected = item.name == selectedItem
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onItemSelected(item.name) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = if (isSelected) item.selectedIconRes ?: item.iconRes else item.iconRes),
                        contentDescription = item.name,
                        tint = if (isSelected) Color.White else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = item.name,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}