package com.example.loginandsignup

import ItemModel
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.loginandsignup.databinding.ActivityUploadFoundItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class UploadFoundItem : AppCompatActivity() {
    private lateinit var binding : ActivityUploadFoundItemBinding

    private var imageUri : Uri? = null

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it

        binding.foundimage.setImageURI(imageUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadFoundItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.foundimage.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.save.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {

        if (binding.heading.text.toString().isEmpty()
            ||binding.description.text.toString().isEmpty()
            ||binding.contact.text.toString().isEmpty()
            ||imageUri == null) {
            Toast.makeText(this, "Information insufficient", Toast.LENGTH_SHORT).show()
        }else{
            uploadImage()
        }
    }

    private fun  uploadImage() {
        val storageRef = FirebaseStorage.getInstance().getReference("found")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .child(System.currentTimeMillis().toString())

        storageRef.putFile(imageUri!!).addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener {
                storeData(it)
            }.addOnFailureListener{
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun storeData(imageUrl: Uri?) {

        val data = ItemModel(
            heading = binding.heading.text.toString(),
            image = imageUrl.toString(),
            description = binding.description.text.toString(),
            contact = binding.contact.text.toString(),
        )

        val heading = binding.heading.text.toString()
        FirebaseDatabase.getInstance().getReference("found")
            .child(heading)
            .setValue(data).addOnCompleteListener{

                if(it.isSuccessful){
                    startActivity(Intent(this, UploadType::class.java))
                    finish()
                    Toast.makeText(this, "You are a good one", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}