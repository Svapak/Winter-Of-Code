package com.example.loginandsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FoundDes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_des)

        val theading : TextView = findViewById(R.id.heading)
        val tcontact : TextView = findViewById(R.id.contact)
        val timage : ImageView = findViewById(R.id.image)
        val tdescription : TextView = findViewById(R.id.description)

        val heading = intent.getStringExtra("heading")
        val contact = intent.getStringExtra("contact")
        val description = intent.getStringExtra("description")
        val image = intent.getStringExtra("image")

        theading.text = heading
        tcontact.text = contact
        tdescription.text = description
        Glide.with(this).load(image).into(timage)
    }
}