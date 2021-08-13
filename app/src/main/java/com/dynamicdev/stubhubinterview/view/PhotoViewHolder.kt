package com.dynamicdev.stubhubinterview.view

import androidx.recyclerview.widget.RecyclerView
import com.dynamicdev.stubhubinterview.databinding.PhotoLayoutBinding
import com.dynamicdev.stubhubinterview.model.PresentationData
import com.squareup.picasso.Picasso

class PhotoViewHolder(private val binding: PhotoLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: PresentationData) {
        binding.photoLikes.text =
            data.likes.toString()
        binding.photoUserName.text =
            data.userName
        Picasso.get().load(
            data.imageUrl
        ).into(binding.photoImage)
    }
}