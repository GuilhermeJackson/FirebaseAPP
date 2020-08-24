package com.example.firebaseautenticacao.data

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class UserRepository {

    private var firebaseAuth = FirebaseAuth.getInstance()

    fun addUser() {
        firebaseAuth.createUserWithEmailAndPassword("jones.burguer@gmail.com", "1020304050").
        addOnCompleteListener {
            if (it.isSuccessful) {
                //
            } else {
                //
            }
        }
    }
}