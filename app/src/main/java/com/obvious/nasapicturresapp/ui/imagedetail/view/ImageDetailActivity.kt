package com.obvious.nasapicturresapp.ui.imagedetail.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.obvious.nasapicturresapp.utils.Constants
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.ActivityImageDetailBinding
import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import com.obvious.nasapicturresapp.ui.imagedetail.view.fragments.DataFragment
import com.obvious.nasapicturresapp.utils.UIHelper

/**
 * Developed by Kajal Kukdeja on 11,April,2022
 * Designed to show swipable view for every image fetched from server
 */

class ImageDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImageDetailBinding
    private lateinit var list : ArrayList<NasaPictureModel>
    private var position = 0

    private var fragment: Fragment? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

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
        if(intent!=null && intent.hasExtra(Constants.ALL_ITEMS)){
            list = intent.getParcelableArrayListExtra(Constants.ALL_ITEMS)!!
            position = intent.getIntExtra(Constants.POSITION, 0)
        }

        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager.setAdapter(viewPagerAdapter)

        binding.viewPager.currentItem = position
    }

    internal inner class ViewPagerAdapter() :
        FragmentStateAdapter(this) {
        @NonNull
        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            fragment = DataFragment.newInstance(list.get(position))
            return fragment!!


        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun getTabView(position: Int): View? {
            return null
        }
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