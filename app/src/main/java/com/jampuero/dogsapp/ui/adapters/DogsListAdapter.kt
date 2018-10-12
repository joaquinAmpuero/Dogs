package com.jampuero.dogsapp.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jampuero.dogsapp.BR
import com.jampuero.dogsapp.R
import com.jampuero.dogsapp.databinding.ViewItemDogsBinding

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsListAdapter : RecyclerView.Adapter<DogsListAdapter.PaymentHolder>() {
    var items: MutableList<String> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsListAdapter.PaymentHolder {
        val binding: ViewItemDogsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_item_dogs, parent, false)
        return PaymentHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DogsListAdapter.PaymentHolder, position: Int) {
        holder.bind(this, position)
    }

    fun setFirstData(hashMap: HashMap<String, List<String>>) {
        val firstDataItems: ArrayList<String> = arrayListOf()
        for (entry in hashMap.entries) {
            entry?.key?.let {
                if (entry.value!!.isNotEmpty()) {
                    for (type in entry.value) {
                        firstDataItems.add(it.plus("-").plus(type))
                    }
                } else {
                    firstDataItems.add(it)
                }
            }
        }
        items = firstDataItems
        this.notifyDataSetChanged()
    }

    private lateinit var listener: OnItemClickListener

    fun addOnCLickItemListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(breed: String)
    }

    inner class PaymentHolder(private val binding: ViewItemDogsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DogsListAdapter, position: Int) {
            binding.setVariable(BR.breed, data.items[position])
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(data.items[position]) }
            }
            binding.executePendingBindings()
        }
    }
}