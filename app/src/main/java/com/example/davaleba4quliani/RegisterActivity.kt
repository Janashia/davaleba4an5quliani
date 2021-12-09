package com.example.davaleba4quliani

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.widget.Button

import android.widget.EditText

import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {


    private lateinit var editTextEmail:EditText

    private lateinit var editTextPassword:EditText

    private lateinit var editTextRepeatPassword:EditText

    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register2)

        init()

        registerListeners()

    }
    private fun init() {

        editTextEmail = findViewById(R.id.editTextEmail)

        editTextPassword = findViewById(R.id.editTextPassword)

        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)

        buttonRegister = findViewById(R.id.buttonRegister)

    }
    private fun registerListeners() {

        buttonRegister.setOnClickListener {


            val repeatpassword = editTextRepeatPassword.text.toString()

            val password = editTextPassword.text.toString()

            val email = editTextEmail.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {

                Toast.makeText(this, "EMPTY!", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            else if (password == repeatpassword && email.contains("@") && password.length > 8) {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)

                        .addOnCompleteListener { task ->

                            if(task.isSuccessful) {

                                startActivity(Intent(this, LoginActivity::class.java))

                                finish()
                            }else {

                                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun passwordValidate(password1: String, password2: String): Boolean {

        when {
            password1.length < 9 -> {
                Toast.makeText(this,"password must have to be , at last 9 characters long", Toast.LENGTH_SHORT).show()
                return false
            }
            !password1.matches(".*[A-Z].*".toRegex()) -> {
                Toast.makeText(this, "password has to contain, one upper-case letter", Toast.LENGTH_SHORT).show()
                return false

            }
            !password1.matches((".*[0-9].*").toRegex()) -> {
                Toast.makeText(this, "password has to contain at least one number", Toast.LENGTH_SHORT).show()
                return false
            }
            !password1.matches(".*[a-z].*".toRegex()) -> {
                Toast.makeText(this, "password has to contain, one down-case letter", Toast.LENGTH_SHORT).show()
                return false
            }
            !password1.matches((".*[!@#$%^&*+=/?].*").toRegex()) -> {
                Toast.makeText(this, "password has to contain at least one symbols ", Toast.LENGTH_SHORT).show()
                return false
            }

            password1 != password2 -> {
                Toast.makeText(this, "password dont much", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }
}


