package org.sopt.at.presentaion.my

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MyScreen(navController: NavController) {
    val myViewModel: MyViewModel = viewModel()
    val userNickname by myViewModel.userNickname.observeAsState("로딩 중...")


    LaunchedEffect(true) {
        Log.d("MyScreen", "Fetching user nickname...")
        myViewModel.fetchUserNickname()

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "프로필 정보",
                color = Color.White,
                fontSize = 28.sp,

                )

            Text(
                modifier = Modifier
                    .padding(top = 24.dp),
                text = "안녕하세요, $userNickname 님!",
                color = Color.White,
                fontSize = 24.sp
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = "환영합니다.",
                color = Color.LightGray,
                fontSize = 16.sp
            )
        }

        Button(
            onClick = {
                navController.navigate("signin") // 로그인 성공 후 Home 화면으로 이동
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent // 배경이 투명
            ),
            border = BorderStroke(1.dp, Color.LightGray) // 테두리 색상
        ) {
            Text("로그아웃", color = Color.LightGray) // 텍스트 색상 지정
        }

    }
}