package com.jampuero.dogsapp.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jampuero.dogsapp.BR
import com.jampuero.dogsapp.R
import com.jampuero.dogsapp.databinding.ViewItemImagesBinding

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsImagesListAdapter : RecyclerView.Adapter<DogsImagesListAdapter.PaymentHolder>() {
    var items: MutableList<String> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsImagesListAdapter.PaymentHolder {
        val binding: ViewItemImagesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_item_images, parent, false)
        return PaymentHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DogsImagesListAdapter.PaymentHolder, position: Int) {
        holder.bind(this, position)
    }

    fun setFirstData(images: List<String>) {
        items = images.toMutableList()
        this.notifyDataSetChanged()
    }

    inner class PaymentHolder(private val binding: ViewItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DogsImagesListAdapter, position: Int) {
            binding.setVariable(BR.url, data.items[position])
            binding.executePendingBindings()
        }
    }
}