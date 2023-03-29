package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.loginandsignup.databinding.ActivityLogOutBinding
import com.google.firebase.auth.FirebaseAuth

class Log_Out : AppCompatActivity() {

    private lateinit var binding: ActivityLogOutBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        if (user.currentUser != null) {
            user.currentUser?.let {
                binding.name.text = it.email
            }
        }

        binding.save.setOnClickListener {
            user.signOut()
            startActivity(
                Intent(
                    this,
                    SignInActivity::class.java
                )
            )
        }
    }
}
