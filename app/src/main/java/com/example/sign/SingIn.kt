package com.example.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SingIn : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY = "com.example.sign.SingIn.KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        val signButton  = findViewById<Button>(R.id.btnSingIn)
        val id = findViewById<TextInputEditText>(R.id.SignID)
        //val pwd = findViewById<TextInputEditText>(R.id.Pwd)

        signButton.setOnClickListener {

            val userId = id.text.toString()
           // val password = pwd.text.toString()

            if(userId.isNotEmpty())
            {
                readData(userId)
            }
            else
            {
                Toast.makeText(this,"Enter User Id",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(userId: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(userId).get().addOnSuccessListener {
            if(it.exists())
            {
                //Database Path
                //val email = it.child("email").value
                val name = it.child("userName").value
                //val userid = it.child("userId").value

                val intentWelcome = Intent(this,WelcomeHome::class.java)

                intentWelcome.putExtra(KEY,name.toString())
                startActivity(intentWelcome)

            }
            else
            {
                Toast.makeText(this,"User Dose not exist",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"It's not U, it's Us",Toast.LENGTH_SHORT).show()
        }

    }
}