package com.obvious.nasapicturresapp.ui.imagegrid.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.obvious.nasapicturresapp.utils.Constants
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.ActivityImageGridBinding
import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import com.obvious.nasapicturresapp.ui.imagedetail.view.ImageDetailActivity
import com.obvious.nasapicturresapp.ui.imagegrid.adapter.ImageAdapter
import com.obvious.nasapicturresapp.ui.imagegrid.viewmodel.ListViewModel
import com.obvious.nasapicturresapp.utils.NetworkResult
import com.obvious.nasapicturresapp.utils.ToastUtils
import com.obvious.nasapicturresapp.utils.UIHelper
import dagger.hilt.android.AndroidEntryPoint

/**
 * Developed by Kajal Kukdeja on 10,April,2022
 * Image list will be shown in this activity
 */

@AndroidEntryPoint
class ImageGridActivity : AppCompatActivity(), ImageAdapter.OnSingleItemClickListener,
    View.OnClickListener {

    private lateinit var binding: ActivityImageGridBinding
    private var list = ArrayList<NasaPictureModel>()
    private lateinit var adapter: ImageAdapter

    private val TAG: String = this.javaClass.simpleName

    private val viewModel by viewModels<ListViewModel>()

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
        setupObserver()
        setupListener()
    }

    private fun setupObserver() {
        viewModel.imageListResponse.observe(this) { response ->

            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        list.addAll(response.data)
                        adapter.notifyDataSetChanged()
                    }
                         binding.loader.root.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    binding.loader.root.visibility = View.GONE
                    ToastUtils.toast(this, resources.getString(R.string.err_msg))
                }

                is NetworkResult.Loading -> {
                    binding.loader.root.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupUI() {
        adapter = ImageAdapter(this, list, this)
        binding.list.adapter = adapter

        viewModel.nasaPictures()

    }

    private fun setupListener() {
        binding.back.setOnClickListener(this)
    }

    override fun onSingleItemClick(position: Int) {
        var intent = Intent(this, ImageDetailActivity::class.java)
        intent.putParcelableArrayListExtra(Constants.ALL_ITEMS, list)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> finish()
        }
    }

}