package com.example.cashlab20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setupListeners()
    }

    private fun setupListeners() {

        val usernameField = findViewById<EditText>(R.id.username2)
        val emailField = findViewById<EditText>(R.id.usernamee)
        val passwordField = findViewById<EditText>(R.id.passwordd)
        val continueButton = findViewById<Button>(R.id.signup)
        val signinButton = findViewById<Button>(R.id.signin)


        // Acciones cuando se presionan los botones
        continueButton.setOnClickListener {
            if (usernameField.text.toString().isEmpty() || passwordField.text.toString().isEmpty() || emailField.text.toString().isEmpty()){
                Toast.makeText(this,"Rellene los campos para registrarse", Toast.LENGTH_SHORT).show()
            }
        }

        signinButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }



}
