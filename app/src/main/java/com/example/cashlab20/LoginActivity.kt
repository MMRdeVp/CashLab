package com.example.cashlab20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupListeners()
    }

    private fun setupListeners() {

        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        val signUpButton = findViewById<Button>(R.id.login2)


        loginButton.setOnClickListener {
            if (usernameField.text.toString().isEmpty() || passwordField.text.toString().isEmpty()){
                Toast.makeText(this,"Rellene los campos para iniciar sesión", Toast.LENGTH_SHORT).show()
            }else{
                //el codigo para comprobar el usuario


                if (usernameField.text.toString().equals("admin") && passwordField.text.toString().equals("admin")){
                    startActivity(Intent(this, MainActivity::class.java))
                }


                //para mantener la app con el usuario logueado incluso si se cierra
                val sharedPref = getSharedPreferences("cashlabpref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }





}
