package com.example.firebaseautenticacao.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticacao.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.example.firebaseautenticacao.ui.cadastrar.CadastrarActivity as CadastrarActivity1


class MainActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val authListener = FirebaseAuth.AuthStateListener {
        val user = auth.currentUser
        user?.let {
            Toast.makeText(this, "Usuário" + user.email + "esta logado", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_login.setOnClickListener {
            val user = auth.currentUser
            if (user == null) {
                val intent = Intent(this, LoginEmailActivity::class.java)
                startActivity(intent)
            } else {
                val intent2 = Intent(this, PrincipalActivity::class.java)
                startActivity(intent2)
            }
        }

        button_cadastrar.setOnClickListener {
            intent = Intent(this, CadastrarActivity1::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        authListener
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth.removeAuthStateListener {
                authListener
            }
        }
    }
}
