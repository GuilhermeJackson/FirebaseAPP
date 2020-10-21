package com.example.firebaseautenticacao.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticacao.R
import com.example.firebaseautenticacao.util.ValidacaoFirebase
import com.example.firebaseautenticacao.util.ValidacaoFirebase.Companion.opcoesErro
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginemail.*

class LoginEmailActivity : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginemail)

        button_OkLogin.setOnClickListener {
            loginEmail(
                editText_EmailLogin.text.toString().trim(),
                editText_SenhaLogin.text.toString().trim()
            )
        }

        button_Recuperar.setOnClickListener{

        }
    }

    private fun loginEmail(email: String, senha: String) {
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()
        } else {
            if (ValidacaoFirebase.verificarInternet(this)) {
                logarUsuario(email, senha)
            } else {
                Toast.makeText(this, "Verifique sua internet", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun logarUsuario(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Usuario logado com sucesso", Toast.LENGTH_LONG).show()
                finish()
            } else {
                opcoesErro(this, it.exception.toString())
            }
        }
    }
}