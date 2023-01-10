package com.example.wlpicture.activity.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.activity.users.LoginActivity
import com.example.wlpicture.databinding.ActivityOnBoardingThirdBinding

class OnBoardingThirdActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityOnBoardingThirdBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMulai.setOnClickListener(){
            startActivity(Intent(this@OnBoardingThirdActivity, LoginActivity::class.java))
            finish()
        }

        binding.btnBack.setOnClickListener(){
            startActivity(Intent(this@OnBoardingThirdActivity, OnBoardingSecondActivity::class.java))
            finish()
        }
    }
}