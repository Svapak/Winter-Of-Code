package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UploadType : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_type)

        var lostupload = findViewById<Button>(R.id.lostupload)
        lostupload.setOnClickListener {
            val intent = Intent( this, UploadLostItem::class.java)
            startActivity(intent)
        }

        var foundupload = findViewById<Button>(R.id.foundupload)
        foundupload.setOnClickListener {
            val intent = Intent( this, UploadFoundItem::class.java)
            startActivity(intent)
        }
    }
}