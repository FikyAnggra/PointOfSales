package com.example.wlpicture.activity.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.wlpicture.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashScreenActivity, OnBoardingFirstActivity::class.java))
            finish()
        }, 5000)

    }
}