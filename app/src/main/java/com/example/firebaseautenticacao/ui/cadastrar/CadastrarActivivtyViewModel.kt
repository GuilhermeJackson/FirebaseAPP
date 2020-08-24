package com.example.firebaseautenticacao.ui.cadastrar

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.firebaseautenticacao.data.UserRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class CadastrarActivivtyViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun cadastrarUsuario(){
        userRepository.addUser()
    }
}