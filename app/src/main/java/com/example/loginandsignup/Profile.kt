package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.loginandsignup.databinding.ActivityProfileBinding
import com.google.firebase.database.FirebaseDatabase

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            validateData()
        }
    }

    private fun validateData(){
        val loading = LoadingDialog(this)
        loading.startLoading()
        if (binding.name.text.toString().isEmpty()
            ||binding.issue.text.toString().isEmpty()
            ||binding.contact.text.toString().isEmpty()) {
            Toast.makeText(this, "Information insufficient", Toast.LENGTH_SHORT).show()
        }else{
            storeData()
        }
    }

    private fun storeData() {

        val data = ProblemModel(
            name = binding.name.text.toString(),
            issue = binding.issue.text.toString(),
            contact = binding.contact.text.toString(),
        )

        val name = binding.name.text.toString()
        FirebaseDatabase.getInstance().getReference("help")
            .child(name)
            .setValue(data).addOnCompleteListener {

                if (it.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    Toast.makeText(this, "We will get back to you soon.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}