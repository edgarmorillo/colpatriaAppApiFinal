package com.example.prueba.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba.R
import com.example.prueba.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompraCarteraScreen(
    navigateToSuccessScreen: () -> Unit,
    navigateToErrorScreen: () -> Unit,
    userViewModel: UserViewModel = viewModel(),
) {
    val scope = rememberCoroutineScope()
    val state by userViewModel.userState.collectAsState()
    val binState by userViewModel.binState.collectAsState()
    val idTransaction by userViewModel.idTransaction.collectAsState()
    val errorMessageAPI by userViewModel.errorMessageAPI.collectAsState()

    val currentAmount = state?.amount
    val amountDebt = 50000

    val textNameCard by remember { mutableStateOf("") }
    var textAmount by remember { mutableStateOf("") }
    var textAmountError by remember { mutableStateOf(false) }
    var textCardNumber by remember { mutableStateOf("") }

    val isColombianCard = binState?.BIN?.country?.alpha2 == "CO"

    var textCardNumberError by remember {
        mutableStateOf(
            textCardNumber.isNotEmpty() && (!isColombianCard || textCardNumber.length !in 15..16),
        )
    }
    LaunchedEffect(key1 = true) {
        userViewModel.getUserInfo("edgar")
    }

    LaunchedEffect(textCardNumber) {
        if (textCardNumber.isNotEmpty()) {
            userViewModel.validateBIN(textCardNumber)
        } else {
            userViewModel.validateBIN("")
        }
    }

    LaunchedEffect(isColombianCard) {
        if (isColombianCard) {
            userViewModel.getTransaction()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = stringResource(id = R.string.topTitle_purchaseDebt),
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                    )
                },
            )
        },
        content = {
            it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.topSubtitle_purchaseDebt),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "$$amountDebt",
                    color = Color.Black,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(15.dp))
                CreditCard(
                    textNameCard = textNameCard,
                    textAmount = textAmount,
                    textCardNumber = textCardNumber,
                )

                if (binState?.BIN?.country != null) {
                    Text(text = "${binState!!.BIN.country}")
                }

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = textAmount,
                    onValueChange = { value ->
                        textAmount = value
                        textAmountError = value.isNotEmpty() && value.toDouble() > currentAmount!!
                    },
                    label = { Text(stringResource(id = R.string.labelFirstInput)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                if (textAmountError) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 12.dp, end = 12.dp),
                        text = "El monto debe ser menor a $$currentAmount",
                        color = Color.Red,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = textCardNumber,
                    onValueChange = { value ->
                        if (value.isEmpty() || value.length <= 16) {
                            textCardNumber = value.filter { it.isDigit() }
                            textCardNumberError =
                                value.isNotEmpty() && (!isColombianCard || value.length !in 15..16)
                        }
                    },
                    label = { Text(stringResource(id = R.string.labelSecondInput)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                if (textCardNumberError) {
                    val color = when {
                        isColombianCard -> Color.Blue
                        binState?.code == 404 -> Color.Red
                        else -> Color.Red
                    }
                    val errorMessage = when {
                        isColombianCard -> stringResource(id = R.string.firstErrorCardNumber)
                        binState?.code == 404 -> stringResource(id = R.string.secondErrorCardNumber)
                        else -> stringResource(id = R.string.finalErrorCardNumber)
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 12.dp, end = 12.dp),
                        text = errorMessage,
                        color = color,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        scope.launch {
                            userViewModel.purchaseTransaction(
                                idTransaction?.id.toString(),
                                navigateToSuccessScreen,
                                navigateToErrorScreen,
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    enabled = !textAmountError && !textCardNumberError && textAmount.isNotEmpty() && textCardNumber.isNotEmpty(),
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(id = R.string.buttonTextPurchaseDebt),
                    )
                }
            }
        },
    )
}

@Composable
fun CreditCard(
    textNameCard: String?,
    textAmount: String?,
    textCardNumber: String?,
    userViewModel: UserViewModel = viewModel(),
) {
    val binState by userViewModel.binState.collectAsState()

    Box(
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFB388FF))
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .height(150.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "$${textAmount ?: "0"}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White,
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                if (binState?.BIN?.brand != null) {
                    val logo = when (binState?.BIN?.brand) {
                        "VISA" -> R.drawable.visa_logo
                        "MASTERCARD" -> R.drawable.mastercard_logo
                        else -> null
                    }
                    logo?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(bottom = 15.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(55.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                binState?.BIN?.issuer?.let {
                    if (textCardNumber != null) {
                        if (textNameCard != null && textCardNumber.isNotEmpty()) {
                            Text(
                                text = it.name,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = Color.White,
                                ),
                            )
                        }
                    }
                }
                Text(
                    text = "**** ${textCardNumber?.takeLast(6)}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.White,
                    ),
                )
            }
        }
    }
}
