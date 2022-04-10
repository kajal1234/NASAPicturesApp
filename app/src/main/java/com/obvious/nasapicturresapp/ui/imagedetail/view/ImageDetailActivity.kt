package com.obvious.nasapicturresapp.ui.imagedetail.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.kontra.utils.Constants
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.ActivityImageDetailBinding
import com.obvious.nasapicturresapp.repository.model.NasaPictureModel
import com.obvious.nasapicturresapp.utils.UIHelper
import kotlin.math.sin

class ImageDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImageDetailBinding
    private lateinit var singleItem : NasaPictureModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
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
        if(intent!=null && intent.hasExtra(Constants.SINGLE_ITEM))
            singleItem = intent.getParcelableExtra(Constants.SINGLE_ITEM)!!

        Glide.with(this).load(singleItem.url).centerCrop().error(R.mipmap.ic_launcher)
            .into(binding.image)

        binding.imageTitle.text = singleItem.title
        binding.imageDesc.text = singleItem.explanation
        binding.imageDate.text = singleItem.date
    }

    private fun setupListener() {
        binding.back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> finish()
        }
    }
}