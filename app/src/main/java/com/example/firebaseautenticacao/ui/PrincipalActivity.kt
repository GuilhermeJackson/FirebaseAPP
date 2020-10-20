package com.example.firebaseautenticacao.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseautenticacao.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        button_deslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }
    }
}
