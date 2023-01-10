package com.example.wlpicture.activity.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wlpicture.activity.users.LoginActivity
import com.example.wlpicture.databinding.ActivityOnBoardingFirstBinding

class OnBoardingFirstActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityOnBoardingFirstBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener(){
            startActivity(Intent(this@OnBoardingFirstActivity, OnBoardingSecondActivity::class.java))
            finish()
        }

        binding.btnLewati.setOnClickListener(){
            startActivity(Intent(this@OnBoardingFirstActivity, LoginActivity::class.java))
            finish()
        }
    }
}