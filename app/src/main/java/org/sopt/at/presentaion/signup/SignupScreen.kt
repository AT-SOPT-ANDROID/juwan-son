package org.sopt.at.presentaion.signup

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.at.ui.theme.TivingTheme

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignupViewModel = viewModel()) {

    var step by remember { mutableStateOf(0) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val currentId by rememberUpdatedState(id)
    val currentPassword by rememberUpdatedState(password)
    val currentNickname by rememberUpdatedState(nickname)
    val signUpState by viewModel.signUpState.observeAsState()

    val isButtonEnabled by remember {
        derivedStateOf { password.length >= 8 }
    }
    LaunchedEffect(signUpState) {
        when (signUpState) {
            is SignupViewModel.SignUpState.Success -> {
                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                navController.navigate("signin")
            }

            is SignupViewModel.SignUpState.Failure -> {
                val message = (signUpState as SignupViewModel.SignUpState.Failure).errorMessage
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            is SignupViewModel.SignUpState.Loading -> {

            }

            else -> Unit
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (step) {
            0 -> {
                Text(
                    "아이디를 입력해주세요.",
                    color = Color.White,
                    fontSize = 20.sp,
                    style = TivingTheme.typography.heading01_M
                )

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
                            text = "아이디",
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
                        if (viewModel.validateId(currentId)) {
                            step = 1
                        } else {
                            Toast.makeText(context, "아이디는 6~12자 사이여야 합니다.", Toast.LENGTH_SHORT)
                                .show()
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
                        val icon =
                            if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),

                    colors = TextFieldDefaults.colors(
                        contentColorFor(backgroundColor = Color.LightGray),
                        unfocusedContainerColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
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

                Text(
                    "영문, 숫자 포함 8~15자리",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        if (viewModel.validatePassword(currentPassword)) {
                            viewModel.signUp(currentId, currentPassword,currentNickname)
                            step = 2

                        } else {
                            Toast.makeText(context, "비밀번호는 8~15자 사이여야 합니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 600.dp)
                        .fillMaxWidth(),
                    enabled = isButtonEnabled,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text("다음", color = Color.White)
                }
            }

            2 -> {
                Text("닉네임을 입력해주세요", color = Color.White, fontSize = 20.sp)

                TextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),

                    colors = TextFieldDefaults.colors(
                        contentColorFor(backgroundColor = Color.LightGray),
                        unfocusedContainerColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White

                    ),

                    placeholder = {
                        Text(
                            text = "닉네임",
                            color = Color.LightGray,
                        )
                    }
                )

                Text(
                    "중복된 닉네임은 불가능합니다",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        if (viewModel.validateNickname(currentNickname)) {
                            viewModel.signUp(currentId,currentPassword,nickname)
                            navController.navigate("signin")

                        } else {
                            Toast.makeText(context, "중복된 닉네임 입니다!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 600.dp)
                        .fillMaxWidth(),
                    enabled = isButtonEnabled,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text("다음", color = Color.White)
                }

            }
        }
    }
}




