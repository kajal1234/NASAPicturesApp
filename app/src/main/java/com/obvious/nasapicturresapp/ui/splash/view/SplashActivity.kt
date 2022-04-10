package com.obvious.nasapicturresapp.ui.splash.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.ActivitySplashBinding
import com.obvious.nasapicturresapp.ui.imagegrid.view.ImageGridActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        setupUI()
    }

    private fun setupUI() {
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            startActivity(Intent(this, ImageGridActivity::class.java))
            finish()
        }, 3000)
    }


}