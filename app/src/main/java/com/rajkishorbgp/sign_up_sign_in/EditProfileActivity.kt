package com.rajkishorbgp.sign_up_sign_in

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: DatabaseReference
    private lateinit var dialog: Dialog
    private lateinit var userData: UserData
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()

        if (uid.isNotEmpty()){
            getUserData()
        }
    }

    private  fun getUserData(){
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userData=snapshot.getValue(UserData::class.java)!!
                binding.firstName.setText(userData.firstName + " " + userData.lastName)
                binding.bio.setText(userData.bio)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}