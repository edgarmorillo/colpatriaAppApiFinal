package com.example.prueba.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba.R
import kotlinx.coroutines.delay

@Composable
fun SuccessCompra(
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navigateToHome()
    }

    Scaffold(
        containerColor = Color(0xFF66BB6A),
        topBar = {},
        content = {
            it
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.messageSuccessTitle),
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.messageSuccessSubtitle),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                )
            }
        },
    )
}
