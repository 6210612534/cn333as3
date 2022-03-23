package com.example.cn333as3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cn333as3.ui.theme.Cn333as3Theme
import kotlin.random.Random
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessing()

        }
    }
}

var random: Int = Random.nextInt(1,1000)
var count = 0
var status = true

@Composable
fun NumberGuessing(){
    val text = remember { mutableStateOf("Try to guess the number I'm thinking of from 1 - 1000")}
    val answer = remember{ mutableStateOf(TextFieldValue())}
    val submit = remember { mutableStateOf("")}

    fun reset(){
        random = Random.nextInt(1,1000)
        text.value = "Try to guess the number I'm thinking of from 1 - 1000"
        count = 0
        status = true
    }

    fun game(){
        if (status) {
            if (answer.value.text.isEmpty()) {
                text.value = "Please Enter your guess number"
            } else {
                if (answer.value.text.toInt() < random) {
                    text.value = "Hint: It's higher!"
                    count++

                } else if (answer.value.text.toInt() > random) {
                    text.value = "Hint: It's lower!"
                    count++

                } else {
                    text.value = "Congratulation! you guess $count times before it's correct"
                    status = false
                }
            }
        }
        else {
            reset()
        }
    }

    Column(
        modifier = Modifier.padding(all = 22.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text.value,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        if(status) {
            TextField(
                value = answer.value,
                onValueChange = {answer.value= it },
                placeholder = {Text("Your guess")},
                singleLine = true ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(8.dp)

            )
        }

        Button(onClick = {game()}) {
            if(status){
                submit.value = "Enter"
                Text(submit.value)
            }
            else{
                submit.value = "Try again"
                Text(submit.value)
            }
        }
    }
}