package org.sopt.at.presentaion.signin


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.sopt.at.R


@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = viewModel()) {

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.observeAsState(SignInViewModel.LoginState.Loading)


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            //
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 12.dp)
                    .padding(top = 16.dp),
            )

            Text(
                textAlign = TextAlign.Left,
                text = "TVING ID 로그인",
                modifier = Modifier
                    .padding(top = 48.dp)
                    .padding(start = 16.dp),
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            TextField(
                value = inputId,
                onValueChange = { inputId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(0.dp)),
                colors = TextFieldDefaults.colors(
                    contentColorFor(backgroundColor = Color.LightGray),
                    unfocusedContainerColor = Color.DarkGray,
                    focusedContainerColor = Color.DarkGray,
                    disabledTextColor = Color.White,
                    disabledLabelColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "아이디",
                        color = Color.LightGray,
                    )
                }
            )


            TextField(
                value = inputPw,
                onValueChange = { inputPw = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.DarkGray),
                colors = TextFieldDefaults.colors(
                    contentColorFor(backgroundColor = Color.LightGray),
                    unfocusedContainerColor = Color.DarkGray,
                    focusedContainerColor = Color.DarkGray,
                    disabledTextColor = Color.White,
                    disabledLabelColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "비밀번호",
                        color = Color.LightGray,
                    )
                }
            )

            Button(
                onClick = {
                    viewModel.signIn(inputId, inputPw)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Gray
                )
            ) {
                Text("로그인하기", color = Color.White)
            }

            when (loginState) {
                is SignInViewModel.LoginState.Loading -> {
                }

                is SignInViewModel.LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("home")
                    }
                }

                is SignInViewModel.LoginState.Failure -> {
                    val errorMessage =
                        (loginState as SignInViewModel.LoginState.Failure).errorMessage
                    Text(errorMessage, color = Color.Red)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "아이디 찾기 |",
                        color = Color.LightGray
                    )
                    Text(
                        text = "비밀번호 찾기 |",
                        color = Color.LightGray
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {
                                navController.navigate("signup")

                            },
                        text = "회원가입",
                        color = Color.LightGray
                    )
                }

                Text(
                    text = "이 사이트는 GoogleCAPTCHA로 보호되며.",
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
                Text(
                    text = "Google 개인정보 처리방침과 서비스 약관이 적용됩니다.",
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
            }
        }

    }
}




