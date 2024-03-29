import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba.R
import com.example.prueba.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navigateToBuyDebt: () -> Unit,
    userViewModel: UserViewModel = viewModel(),
) {
    val userState by userViewModel.userState.collectAsState()

    // Llamar a getUserInfo al cargar la pantalla
    LaunchedEffect(key1 = true) {
        userViewModel.getUserInfo("edgar")
    }

    // Si se ha cargado el usuario, mostramos el dashboard
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = userState?.name ?: "",
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { },
                        enabled = false,
                        content = {
                            Icon(
                                Icons.Outlined.Notifications,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        },
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
            ) {
                userState?.let { user ->
                    Text(
                        text = stringResource(id = R.string.yourBalance_subtitle),
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Left,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    CreditCard()
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = stringResource(id = R.string.purchaseDebt_subtitle),
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Left,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    CardPurchaseButton(navigateToBuyDebt)
                }
            }
        },
    )
}

@Composable
fun CreditCard(
    userViewModel: UserViewModel = viewModel(),
) {
    val user by userViewModel.userState.collectAsState()

    Box(
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF7C4DFF))
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
                    text = "$${user?.amount}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White,
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.visa_logo),
                    contentDescription = "Visa Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(bottom = 15.dp),
                )
            }

            Spacer(modifier = Modifier.height(55.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${user?.name}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.White,
                    ),
                )
                Text(
                    text = "**** ${user?.card_number?.takeLast(6)}",
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

@Composable
fun CardPurchaseButton(
    navigateToBuyDebt: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(1.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFEDE7F6))
            .padding(8.dp)
            .clickable { navigateToBuyDebt() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFFD1C4E9))
                    .align(Alignment.CenterVertically),
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Default.Lock,
                    contentDescription = "",
                    tint = Color.Black,
                )
            }
            Spacer(
                modifier = Modifier.width(20.dp),
            )
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Disponible",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$50.000",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Forward Arrow",
                tint = Color.Black,
            )
        }
    }
}
