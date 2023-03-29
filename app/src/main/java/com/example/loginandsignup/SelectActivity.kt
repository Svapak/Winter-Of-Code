package com.example.loginandsignup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.loginandsignup.databinding.ActivitySelectBinding


class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Lost())


        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId) {

                R.id.search -> replaceFragment(Lost())
                R.id.diamond -> replaceFragment(Found())

                else -> {


                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}