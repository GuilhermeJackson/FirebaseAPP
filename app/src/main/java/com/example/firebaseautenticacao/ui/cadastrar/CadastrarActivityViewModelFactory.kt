package com.example.firebaseautenticacao.ui.cadastrar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseautenticacao.data.UserRepository

class CadastrarActivityViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastrarActivivtyViewModel::class.java)) {
            return CadastrarActivivtyViewModel(
                userRepository
            )
             as T
        }
        throw IllegalArgumentException("Class ViewModel desconhecida")
    }
}