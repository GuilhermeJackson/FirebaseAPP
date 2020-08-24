package com.example.firebaseautenticacao.ui.cadastrar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseautenticacao.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {
    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        button_CadastrarUsuario.setOnClickListener {
            var email = editText_EmailCadastro.getText().toString().trim()
            var senha = editText_SenhaCadastro.getText().toString().trim()
            var confirmarSenha = editText_SenhaRepetirCadastro.getText().toString().trim()
            cadastrar(email, senha, confirmarSenha)
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
                criarUsuario(email, senha)
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
                Toast.makeText(this, "Não foi possível cadastrar, Tente novamente!.", Toast.LENGTH_LONG).show()
            }
        }

    }

//    private fun setupSplashViewModel() {
//        viewModel = viewModelFactory {
//            ViewModelProvider(this,
//                CadastrarActivityViewModelFactory
//            ).get(CadastrarActivivtyViewModel::class.java)
//        }
//    }
}