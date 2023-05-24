package com.rajkishorbgp.sign_up_sign_in

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this,UserProfileActivity::class.java)
                        intent.putExtra("EMAIL",email)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
            }
        }

        binding.forgotPassword.setOnClickListener{
            startActivity(Intent(this,ForgotActivity::class.java))
        }

        binding.signupRedirectText.setOnClickListener{
            val  signUpIntent = Intent(this,SignUpActivity::class.java)
            startActivity(signUpIntent)
            finish()
        }
        
    }
    override fun onStart() {
        super.onStart()
        if (intent.getStringExtra("key")==null) {
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this, UserProfileActivity::class.java))
                finish()
            }
        }
    }
}
