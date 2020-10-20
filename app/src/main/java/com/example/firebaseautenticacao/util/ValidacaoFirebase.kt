package com.example.firebaseautenticacao.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class ValidacaoFirebase {

    companion object {
        fun verificarInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        fun opcoesErro(context: Context, resposta: String) {
            if (resposta.contains("least 6 characters")) {
                Toast.makeText(context, "Crie uma senha maior que 5 dígitos.", Toast.LENGTH_LONG)
                    .show()

            } else if (resposta.contains("address is badly")) {
                Toast.makeText(context, "E-mail inválido.", Toast.LENGTH_LONG).show()

            } else if (resposta.contains("interrupted connection")) {
                Toast.makeText(context, "Conexão perdida com o banco de dados.", Toast.LENGTH_LONG).show()

            } else if (resposta.contains("password is invalid")) {
                Toast.makeText(context, "Senha inválida.", Toast.LENGTH_LONG).show()

            } else if (resposta.contains("There is no user")) {
                Toast.makeText(context, "Este e-mail não está cadastrado.", Toast.LENGTH_LONG).show()

            } else {
                Log.d("ERRO Firebase", resposta)
                Toast.makeText(
                    context, "Ocorreu um erro inesperado, por favor, tente novamente mais tarde",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}