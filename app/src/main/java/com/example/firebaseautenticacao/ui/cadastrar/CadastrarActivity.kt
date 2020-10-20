package com.example.firebaseautenticacao.ui.cadastrar

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseautenticacao.R
import com.example.firebaseautenticacao.util.ValidacaoFirebase
import com.example.firebaseautenticacao.util.ValidacaoFirebase.Companion.opcoesErro
import com.example.firebaseautenticacao.util.ValidacaoFirebase.Companion.verificarInternet
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {
    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        button_CadastrarUsuario.setOnClickListener {
            cadastrar(
                editText_EmailCadastro.getText().toString().trim(),
                editText_SenhaCadastro.getText().toString().trim(),
                editText_SenhaRepetirCadastro.getText().toString().trim()
            )
        }
        button_Cancelar.setOnClickListener {
            finish()
        }
    }

    fun cadastrar(email: String, senha: String, confirmarSenha: String) {
        if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos corretamente!!!", Toast.LENGTH_LONG).show()
        } else {
            if (senha.contentEquals(confirmarSenha)) {
                if (verificarInternet(this)) {
                    criarUsuario(email, senha)
                } else {
                    Toast.makeText(this, "Verifique sua internet", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "As senhas devem ser iguais!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun criarUsuario(email: String, senha: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Cadastrado com sucesso.", Toast.LENGTH_LONG).show()
                finish()
            } else {
                opcoesErro(this, it.exception.toString())
            }
        }
    }
}