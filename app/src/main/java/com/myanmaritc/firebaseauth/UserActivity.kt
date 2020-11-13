package com.myanmaritc.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase

    lateinit var dbRef: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        database = Firebase.database

        auth = Firebase.auth

        dbRef = database.getReference("Users").child(auth.currentUser!!.uid)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value

                var name =
                    "${snapshot.child("firstName").value}${snapshot.child("lastName").value}"

                txtUser.text = name

//                val user=snapshot.getValue(User::class.java)

//                txtUser.text = user!!.firstName
            }

        })

    }
}