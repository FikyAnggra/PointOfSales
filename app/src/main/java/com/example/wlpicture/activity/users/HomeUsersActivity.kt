package com.example.wlpicture.activity.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.databinding.ActivityHomeUserBinding

class HomeUsersActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeUserBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cardProfile.setOnClickListener(){
            startActivity(Intent(this@HomeUsersActivity, ProfileActivity::class.java))
            finish()
        }

        binding.cardLayanan.setOnClickListener(){
            startActivity(Intent(this@HomeUsersActivity, LayananActivity::class.java))
            finish()
        }

        binding.cardReservasi.setOnClickListener(){
            startActivity(Intent(this@HomeUsersActivity, ReservasiActivity::class.java))
            finish()
        }

        binding.cardTimKami.setOnClickListener(){
            startActivity(Intent(this@HomeUsersActivity, TimKamiActivity::class.java))
            finish()
        }
    }
}