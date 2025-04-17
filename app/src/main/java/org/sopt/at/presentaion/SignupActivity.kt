package org.sopt.at.presentaion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme




class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ATSOPTANDROIDTheme {
        SignUpScreen()
    }
}



@Composable
fun SignUpScreen() {
    // 여긴 fragment 가 없나
    var step by remember { mutableStateOf(0) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
            .padding(top=24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (step) {
            0 -> {
                Text("아이디를 입력해주세요.", color = Color.White, fontSize = 20.sp)

                TextField(
                    value = id,
                    onValueChange = { id = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),

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

                Text(
                    "영문 소문자 또는 영문+숫자 조합 6~12자리",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        if (id.length in 6..12) {
                            step = 1
                        }else {

                            Toast.makeText(context, "아이디는 6~12자 사이여야 합니다.", Toast.LENGTH_SHORT).show()
                        }

                    },
                    modifier = Modifier
                        .padding(top = 600.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text("다음", color = Color.White)
                }
            }

            1 -> {
                Text("비밀번호를 입력해주세요.", color = Color.White, fontSize = 20.sp)

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                    ,

                    colors = TextFieldDefaults.colors(
                        contentColorFor(backgroundColor = Color.LightGray),
                        unfocusedContainerColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
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

                Text(
                    "영문, 숫자, 특수문자 포함 8~15자리",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        if (password.length in 8..15) {
                            val resultIntent = Intent().apply {
                                putExtra("id", id)
                                putExtra("password", password)
                            }
                            (context as Activity).setResult(Activity.RESULT_OK, resultIntent)
                            (context as Activity).finish()

                        }else {

                            Toast.makeText(context, "비밀번호는 8~15자 사이여야 합니다.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 600.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text("다음", color = Color.White)
                }
            }
        }
    }
}

