package com.obvious.nasapicturresapp.ui.imagegrid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.nasapicturresapp.R
import com.obvious.nasapicturresapp.databinding.SingleImageItemBinding
import com.obvious.nasapicturresapp.data.model.NasaPictureModel

/**
 * Developed by Kajal Kukdeja on 10,April,2022
 * Image list adapter to adapt data on screen
 */

internal class ImageAdapter(
    val context: Context,
    val list: ArrayList<NasaPictureModel>,
    val onSingleItemClickListener: OnSingleItemClickListener
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    interface OnSingleItemClickListener{
        fun onSingleItemClick(position: Int)
    }

    internal inner class ImageViewHolder(binding: SingleImageItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        var binding : SingleImageItemBinding

        init {
            this.binding = binding
            this.binding.imageLayout.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onSingleItemClickListener.onSingleItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var binding : SingleImageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.single_image_item, null, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(list.get(position).url).centerCrop().error(R.mipmap.ic_launcher)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}