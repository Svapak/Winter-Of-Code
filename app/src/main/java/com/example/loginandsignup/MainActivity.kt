package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var findout = findViewById<Button>(R.id.findout)
        findout.setOnClickListener {
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
        }

        var upload = findViewById<Button>(R.id.upload)
        upload.setOnClickListener {
            val intent = Intent(this, UploadType::class.java)
            startActivity(intent)
        }

        var profile = findViewById<Button>(R.id.profile)
        profile.setOnClickListener{
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        var logOut = findViewById<Button>(R.id.Logout)
        logOut.setOnClickListener {
            val intent = Intent(this, Log_Out::class.java)
            startActivity(intent)
        }
    }
}