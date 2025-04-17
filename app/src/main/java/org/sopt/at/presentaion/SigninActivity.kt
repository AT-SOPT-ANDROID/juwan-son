package org.sopt.at.presentaion

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.presentaion.home.HomeActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SigninActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SignInScreen() {

    var userId by remember { mutableStateOf("") }
    var userPw by remember { mutableStateOf("") }
    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val signUpLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            userId = data?.getStringExtra("id") ?: ""
            userPw = data?.getStringExtra("password") ?: ""
        }
    }

    Scaffold( modifier = Modifier.fillMaxSize(),
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
                //속성 부르는거 주의
                painter = painterResource(id= R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 12.dp)
                    .padding(top=16.dp),
            )

            Text(
                textAlign = TextAlign.Left,
                text="TVING ID 로그인",
                modifier = Modifier
                    .padding(top=48.dp)
                    .padding(start = 16.dp),
                color = Color.White,
                style = TextStyle(
                    fontSize=24.sp,
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
                        text="아이디",
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
                        text="비밀번호",
                        color = Color.LightGray,
                    )
                }
            )

            Button(
                onClick = {
                    if (inputId == userId && inputPw == userPw && userId.isNotEmpty() && userPw.isNotEmpty()) {
                        val intent = Intent(context, HomeActivity::class.java)
                        intent.putExtra("id", inputId)
                        context.startActivity(intent)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.")
                        }
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Gray
                )
            ){
                Text("로그인하기", color=Color.White)
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
                                signUpLauncher.launch(Intent(context, SignupActivity::class.java))
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




@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ATSOPTANDROIDTheme  {
        SignInScreen()


    }
}
