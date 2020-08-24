package com.example.firebaseautenticacao.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseautenticacao.R
import kotlinx.android.synthetic.main.activity_main.*
import com.example.firebaseautenticacao.ui.cadastrar.CadastrarActivity as CadastrarActivity1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_login.setOnClickListener {

        }

        button_cadastrar.setOnClickListener {
            intent = Intent(this, CadastrarActivity1::class.java)
            startActivity(intent)
        }
    }
}
