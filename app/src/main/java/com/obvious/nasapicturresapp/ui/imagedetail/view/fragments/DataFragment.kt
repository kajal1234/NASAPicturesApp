package com.obvious.nasapicturresapp.ui.imagedetail.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.obvious.nasapicturresapp.utils.Constants
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import com.obvious.nasapicturresapp.databinding.FragmentDataBinding

/**
 * Developed by Kajal Kukdeja on 11,April,2022
 * Data fragment designed to show every image detail with view pager
 */

class DataFragment : Fragment() {
    private var singleItem: NasaPictureModel? = null
    private lateinit var binding: FragmentDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            singleItem = it.getParcelable(Constants.SINGLE_ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, null, false)

          Glide.with(this).load(singleItem?.url).centerCrop().error(R.mipmap.ic_launcher)
              .into(binding.image)

          binding.imageTitle.text = singleItem?.title
          binding.imageDesc.text = singleItem?.explanation
          binding.imageDate.text = singleItem?.date
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: NasaPictureModel) =
            DataFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.SINGLE_ITEM, param1)
                }
            }
    }
}