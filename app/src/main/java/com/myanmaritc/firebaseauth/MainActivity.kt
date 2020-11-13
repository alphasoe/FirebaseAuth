package com.myanmaritc.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
    }

    private fun initFirebase() {

        auth = Firebase.auth

//        sign_up.setOnClickListener {
//            var email: String = email.text.toString()
//            var password: String = email_password.text.toString()
//
//            createAccount(email, password)
//        }

        sign_in.setOnClickListener {
            var email: String = email.text.toString()
            var password: String = email_password.text.toString()

            signinAccount(email, password)
        }

        btnSignUp.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }


    private fun signinAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Success signin", Toast.LENGTH_LONG).show()
                    val userId = auth.currentUser?.uid

                    val intent=Intent(this,UserActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Signin Fail" + task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }
}