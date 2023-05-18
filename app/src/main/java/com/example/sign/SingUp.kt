package com.example.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SingUp : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val email = findViewById<TextInputEditText>(R.id.Email)
        val userId = findViewById<TextInputEditText>(R.id.UserID)
        val userName = findViewById<TextInputEditText>(R.id.UserName)
        val password = findViewById<TextInputEditText>(R.id.Password)
        val singBTN = findViewById<Button>(R.id.btnSingUp)

        singBTN.setOnClickListener {

            val name = userName.text.toString()
            val mail = email.text.toString()
            val id = userId.text.toString()
            val pwd = password.text.toString()

            val user = User(id,name,mail,pwd)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(id).setValue(user).addOnSuccessListener {
                userName.text?.clear()
                email.text?.clear()
                userId.text?.clear()
                password.text?.clear()

                Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }

        val login = findViewById<TextView>(R.id.LogIn)

        login.setOnClickListener {
            val intentLogIn = Intent(this,SingIn::class.java)
            startActivity(intentLogIn)
        }

    }
}