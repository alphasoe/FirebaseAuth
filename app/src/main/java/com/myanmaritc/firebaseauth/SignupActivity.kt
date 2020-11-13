package com.myanmaritc.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.email
import kotlinx.android.synthetic.main.activity_main.email_password
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initFirebase()
    }

    private fun initFirebase() {

        auth = Firebase.auth

        sign_up.setOnClickListener {
            var email: String = email.text.toString()
            var password: String = email_password.text.toString()

            val firstName: String = edtFirstName.text.toString()
            val lastName: String = edtLastName.text.toString()
            val ph_no: String = edtPhNO.text.toString()

            createAccount(email, password, firstName, lastName, ph_no)
        }

//        sign_in.setOnClickListener {
//            var email: String = email.text.toString()
//            var password: String = email_password.text.toString()
//
//            signinAccount(email, password)
//        }
    }

    private fun createAccount(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        ph_no: String
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successful signup", Toast.LENGTH_LONG).show()
                    val userId = auth.currentUser?.uid

                    val database = Firebase.database

                    val ref = database.getReference("Users")

                    val userDB = ref.child(userId!!)

                    userDB.child("firstName").setValue(firstName)
                    userDB.child("lastName").setValue(lastName)
                    userDB.child("ph_no").setValue(ph_no)
                    userDB.child("userId").setValue(userId)

                    finish()


                } else {
                    Toast.makeText(this, "Fail Signup", Toast.LENGTH_LONG).show()
                }
            }
    }
}