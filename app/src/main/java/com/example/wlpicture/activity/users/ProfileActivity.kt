package com.example.wlpicture.activity.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityProfileBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(){
            startActivity(Intent(this@ProfileActivity, HomeUsersActivity::class.java))
            finish()
        }
    }
}