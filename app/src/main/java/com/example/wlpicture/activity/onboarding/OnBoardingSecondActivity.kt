package com.example.wlpicture.activity.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.activity.users.LoginActivity
import com.example.wlpicture.databinding.ActivityOnBoardingSecondBinding

class OnBoardingSecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityOnBoardingSecondBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener(){
            startActivity(Intent(this@OnBoardingSecondActivity, OnBoardingThirdActivity::class.java))
            finish()
        }

        binding.btnLewati.setOnClickListener(){
            startActivity(Intent(this@OnBoardingSecondActivity, LoginActivity::class.java))
            finish()
        }

        binding.btnBack.setOnClickListener(){
            startActivity(Intent(this@OnBoardingSecondActivity, OnBoardingFirstActivity::class.java))
            finish()
        }
    }
}