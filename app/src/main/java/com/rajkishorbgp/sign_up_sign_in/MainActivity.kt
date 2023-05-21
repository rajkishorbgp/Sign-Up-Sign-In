package com.rajkishorbgp.sign_up_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        binding.logoutButton.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(this,SignInActivity::class.java))
            Toast.makeText(this,"Log out!",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}