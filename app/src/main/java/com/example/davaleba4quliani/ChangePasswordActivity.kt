package com.example.davaleba4quliani
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var editTextNewPassword:EditText
    private lateinit var buttonNewPassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
        registerListeners()
    }
    private fun init() {
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        buttonNewPassword = findViewById(R.id.buttonNewPassword)

    }
    private fun registerListeners() {
        buttonNewPassword.setOnClickListener {

            val newPassword = editTextNewPassword.text.toString()

            if(newPassword.isEmpty() || newPassword.length < 6) {
                Toast.makeText(this, "Incorrect new password ", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }




































}