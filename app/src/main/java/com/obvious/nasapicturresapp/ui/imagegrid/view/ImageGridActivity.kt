package com.obvious.nasapicturresapp.ui.imagegrid.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.ActivityImageGridBinding
import com.obvious.nasapicturresapp.ui.imagedetail.view.ImageDetailActivity
import com.obvious.nasapicturresapp.ui.imagegrid.adapter.ImageAdapter
import com.obvious.nasapicturresapp.utils.UIHelper

class ImageGridActivity : AppCompatActivity(), ImageAdapter.OnSingleItemClickListener,
    View.OnClickListener {

    private lateinit var binding: ActivityImageGridBinding
    private var list = ArrayList<String>()
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_grid)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            UIHelper.dpToPx(this, 66.00).toInt()
        )
        params.setMargins(0, UIHelper.getStatusBarHeight(this), 0, 0)
        binding.toolbar.setLayoutParams(params)

        setupUI()
        setupListener()
    }

    private fun setupUI() {
        adapter = ImageAdapter(this, list, this)
        binding.list.adapter = adapter
    }

    private fun setupListener() {
        binding.back.setOnClickListener(this)
    }

    override fun onSingleItemClick(position: Int) {
        startActivity(Intent(this, ImageDetailActivity::class.java))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> finish()
        }
    }

}