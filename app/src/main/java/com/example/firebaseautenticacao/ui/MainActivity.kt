package com.example.firebaseautenticacao.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseautenticacao.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
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

        validateLoginUser()
        registerUser()

        cardView_LoginGoogle.setOnClickListener{
            serviceGoogle()
        }


    }
    private fun validateLoginUser() {
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
    }

    private fun registerUser(){
        button_cadastrar.setOnClickListener {
            intent = Intent(this, CadastrarActivity1::class.java)
            startActivity(intent)
        }
    }

    private fun serviceGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account == null) {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 555)
        } else {
            Toast.makeText(this, "Já esta logado", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, PrincipalActivity::class.java))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 555) {
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.result
                startActivity(Intent(this, PrincipalActivity::class.java))
            } catch (e: ApiException){
                Toast.makeText(this, "Erro ao logar com a conta do Google", Toast.LENGTH_LONG).show()
            }
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
