package com.rivaldofez.moviers.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.databinding.ActivitySplashBinding
import com.rivaldofez.moviers.ui.home.HomeActivity
import com.rivaldofez.moviers.utils.showLoading

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_TIME : Long = 3000
    }

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        Glide.with(this).load(R.drawable.logo_moviers).into(splashBinding.imgLogoSplash)
        showLoading(true, splashBinding.loading)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            showLoading(false, splashBinding.loading)
        }, SPLASH_TIME)
    }
}