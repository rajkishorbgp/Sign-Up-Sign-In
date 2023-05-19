package com.rajkishorbgp.sign_up_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        binding.resetPassword.setOnClickListener{
            val email=binding.resetEmail.text.toString()
            if (email.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                    if (it.isSuccessful){
                        startActivity(Intent(this,SignInActivity::class.java))
                        Toast.makeText(this,"Check your email to reset your password!",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show()
            }
        }
    }
}