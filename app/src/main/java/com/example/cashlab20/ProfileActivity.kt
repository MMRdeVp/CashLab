package com.example.cashlab20

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.IconCompat
import androidx.core.graphics.drawable.IconCompat.IconType
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SetupListeners()

    }

    private fun SetupListeners() {

        val volver = findViewById<ImageView>(R.id.volver)

        val editUsername = findViewById<ImageView>(R.id.editUsername)
        val editpfp = findViewById<ImageView>(R.id.editPfp)
        val editEmail = findViewById<ImageView>(R.id.editEmail)
        val editPassword = findViewById<ImageView>(R.id.editPassword)

        val logout = findViewById<TextView>(R.id.LogOut)
        val deleteacc = findViewById<TextView>(R.id.DeleteAccount)

        volver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //FALTAN EDITAR PARAMETROS DEL USUARIO -- POR CORREO??

        logout.setOnClickListener{
            val sharedPref = getSharedPreferences("cashlabpref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        deleteacc.setOnClickListener{

        }

    }
}