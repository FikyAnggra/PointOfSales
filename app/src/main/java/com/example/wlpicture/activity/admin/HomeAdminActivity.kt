package com.example.wlpicture.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.databinding.ActivityHomeAdminBinding

class HomeAdminActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeAdminBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.cardTimKami.setOnClickListener(){
            startActivity(Intent(this@HomeAdminActivity, TimKamiAdminActivity::class.java))
            finish()
        }
    }
}