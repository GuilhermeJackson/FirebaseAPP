package com.example.firebaseautenticacao.ui

import android.os.Bundle
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
            logarUsuario(email, senha)
        }
    }

    private fun logarUsuario(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Usuario logado com sucesso", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Erro ao logar com esse usuario", Toast.LENGTH_LONG).show()
            }
        }
    }
}