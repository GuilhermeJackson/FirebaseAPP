package com.example.firebaseautenticacao.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticacao.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginemail.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginEmailActivity : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginemail)

        button_OkLogin.setOnClickListener {
            loginEmail(
                editText_EmailLogin.getText().toString(),
                editText_SenhaLogin.getText().toString()
            )
        }
    }

    private fun loginEmail(email: String, senha: String){
        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()

        } else {
            if (verificarInternet()){
                logarUsuario(email, senha)

            } else {
                Toast.makeText(this, "Verifique sua internet", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun logarUsuario(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, PrincipalActivity::class.java))
                Toast.makeText(this, "Usuario logado com sucesso", Toast.LENGTH_LONG).show()
                finish()
            } else {
                opcoesErro(it.exception.toString())
            }
        }
    }

    private fun opcoesErro(resposta: String) {
        if (resposta.contains("least 6 characters")) {
            Toast.makeText(this, "Crie uma senha maior que 5 dígitos.", Toast.LENGTH_LONG)
                .show()

        } else if (resposta.contains("address is badly")) {
            Toast.makeText(this, "E-mail inválido.", Toast.LENGTH_LONG).show()

        } else if (resposta.contains("interrupted connection")) {
            Toast.makeText(this, "Conexão perdida com o banco de dados.", Toast.LENGTH_LONG).show()

        } else if (resposta.contains("password is invalid")){
            Toast.makeText(this, "Senha inválida.", Toast.LENGTH_LONG).show()
        } else if (resposta.contains("There is no user")) {
            Toast.makeText(this, "Este e-mail não está cadastrado.", Toast.LENGTH_LONG).show()

        } else {
            Log.d("Erro ao logar", resposta)
            Toast.makeText(this, resposta , Toast.LENGTH_LONG).show()

        }
    }

    private fun verificarInternet(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}