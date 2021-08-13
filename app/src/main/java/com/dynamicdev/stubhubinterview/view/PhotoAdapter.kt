package com.dynamicdev.stubhubinterview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdev.stubhubinterview.databinding.PhotoLayoutBinding
import com.dynamicdev.stubhubinterview.databinding.PhotoLayoutEmptyBinding
import com.dynamicdev.stubhubinterview.model.PresentationData

class PhotoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet: List<PresentationData>? = null

    fun setDataSet(dataSet: List<PresentationData>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    /**
     * @return 0 for empty viewholder
     * @return size for photo viewholder
     */
    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return dataSet?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            0 -> createEmptyViewHolder(parent)
            else -> createPhotoViewHolder(parent)
        }
    }

    private fun createPhotoViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PhotoViewHolder(
            PhotoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PhotoViewHolderEmpty(
            PhotoLayoutEmptyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is PhotoViewHolder -> {
                dataSet?.let {
                    holder.onBind(it[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }
}