package com.example.textfieldsbuttonsshowingsnackbars_androidjetpackcompose_part7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val textFieldState = remember { mutableStateOf("") }
            val scope = rememberCoroutineScope()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState,
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        TextField(

                            value = textFieldState.value,
                            onValueChange = { newValue -> textFieldState.value = newValue },
                            label = { Text(text = "Enter your name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().padding(50.dp)

                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("Hello, ${textFieldState.value}")/////Snackbar, kullanıcı etkileşimli olduğu için değil, kullanıcıya bir geri bildirim mesajı göstermek için kullanıldığı için coroutine içinde kullanılmıştır. Snackbar, kullanıcının uygulamada yaptığı bir eylemi tamamladıktan sonra gösterilen bir mesajdır ve bu mesaj, kullanıcının ekranı üzerinde gösterilir. Snackbar'ı CoroutineScope içinde kullanmanın sebebi, Snackbar'ın gösterilmesinin bir işlem olduğu ve bu işlemin arka planda yapılması gerektiği içindir. Böylece, işlem UI thread'i bloke etmez ve kullanıcı arayüzü üzerinde herhangi bir aksaklık oluşmaz.
                                }
                            }
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            )
        }
    }
}


