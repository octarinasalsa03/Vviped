package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.user_email
import kotlinx.android.synthetic.main.activity_login.user_password


class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_login_account.setOnClickListener{
            val email = user_email.text.toString().trim()
            val password = user_password.text.toString().trim()

            if(email.isEmpty()){
                user_email.error = "Email field cannot be empty"
                user_email.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                user_email.error = "Email not valid"
                user_email.requestFocus()
                return@setOnClickListener
            }

            if(password.length < 6){
                user_password.error = "Password too short"
                user_password.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                user_password.error = "Password field cannot be empty"
                user_password.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,password)
        }

        textCreateAccount.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()

                }
            }
    }
    // if user has logged in
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null)
            startActivity(Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })

    }

}