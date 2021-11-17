package com.example.tufinancieromobileapp.data.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel(){
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user
    val randsId = (0..999999).random()+156

    suspend fun signIn(email: String, displayName: String){
        delay(2000)//simulating network call
        _user.value = User(
            randsId,
            displayName,
            "Perez",
            email,
            "sdsadsadsa",
            "Hola: yo soy: $displayName"
        )
    }
}