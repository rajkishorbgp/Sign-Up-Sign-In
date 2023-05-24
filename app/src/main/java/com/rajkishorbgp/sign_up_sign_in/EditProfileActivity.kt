package com.rajkishorbgp.sign_up_sign_in

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivityEditProfileBinding
import java.time.Instant

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener{
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val age = binding.userAge.text.toString()
            val userName = binding.userName.text.toString()
            val email = intent.getStringExtra("EMAIL")

            databaseReference=FirebaseDatabase.getInstance().getReference("Users")
            val user =User(firstName,lastName,age,userName,email)
            databaseReference.child(userName).setValue(user).addOnSuccessListener {

                val intant = Intent(this,UserProfileActivity::class.java)
                intant.putExtra("USER",userName)
                startActivity(intant)
                finish()
                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}