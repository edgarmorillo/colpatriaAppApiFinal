package com.example.prueba.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prueba.data.model.BINResponse
import com.example.prueba.data.model.FinalTransactionResponse
import com.example.prueba.data.model.TransactionResponse
import com.example.prueba.data.model.User
import com.example.prueba.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {

    private val _errorMessageAPI = MutableStateFlow("")
    val errorMessageAPI = _errorMessageAPI.asStateFlow()

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    private val _binState = MutableStateFlow<BINResponse?>(null)
    val binState: StateFlow<BINResponse?> = _binState

    private val _idTransaction = MutableStateFlow<TransactionResponse?>(null)
    val idTransaction: StateFlow<TransactionResponse?> = _idTransaction

    private val _messagePurchase = MutableStateFlow<FinalTransactionResponse?>(null)
    val messagePurchase: StateFlow<FinalTransactionResponse?> = _messagePurchase

    fun getUserInfo(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getUserInfo(name)
                if (response.isSuccessful) {
                    val user = response.body()
                    _userState.value = user
                } else {
                    // Manejar el caso en que la llamada no fue exitosa

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun validateBIN(bin: String): Boolean {
        try {
            val response = RetrofitClient.binCheckerService.checkBin(bin)
            if (response.isSuccessful) {
                val binCard = response.body()
                _binState.value = binCard
            } else {
                if (response.code() == 400) {
                    val errorMessage = response.errorBody()?.string()
                    if (errorMessage != null) {
                        _errorMessageAPI.value = errorMessage
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    if (errorMessage != null) {
                        _errorMessageAPI.value = errorMessage
                    }
                }
            }
        } catch (e: Exception) {
            // Manejar errores de la llamada al servicio
            e.printStackTrace()
        }
        return false
    }

    suspend fun getTransaction() {
        try {
            val response = RetrofitClient.apiService.getTransactionInfo()
            if (response.isSuccessful) {
                val id = response.body()
                _idTransaction.value = id
            } else {
                // Manejar el caso en que la llamada no fue exitosa

            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Manejar cualquier error que ocurra durante la obtención de la información del usuario
        }
    }

    suspend fun purchaseTransaction(
        id: String,
        navigateToSuccessScreen: () -> Unit,
        navigateToErrorScreen: () -> Unit,
    ) {
        try {
            val response = RetrofitClient.apiService.finishTransactionInfo(transaction_id = id)
            if (response.isSuccessful) {
                val message = response.body()
                _messagePurchase.value = message
                if (message != null) {
                    if (message.message == "Compra de cartera exitosa") {
                        navigateToSuccessScreen()
                    } else {
                        navigateToErrorScreen()
                    }
                } else {
                    navigateToErrorScreen()
                }
            } else {
                navigateToErrorScreen()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Manejar cualquier error que ocurra durante la obtención de la información del usuario
        }
    }
}
