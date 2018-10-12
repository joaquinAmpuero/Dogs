package com.jampuero.dogsapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.jampuero.dogsapp.R
import com.jampuero.dogsapp.databinding.ActivityDogsListBinding
import com.jampuero.dogsapp.viewmodel.DogsListViewModel
import com.jampuero.dogsapp.ui.adapters.DogsListAdapter

class DogsListView : AppCompatActivity(), DogsListAdapter.OnItemClickListener, Messages {
    override fun showSnackBarMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onItemClick(breed: String) {
        val intent = Intent(this, DogsBreedsImagesView::class.java)
        intent.putExtra("breed", breed)
        startActivity(intent)
    }

    private lateinit var listViewModel: DogsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs_list)
        initViewModel()
        val binding: ActivityDogsListBinding = DataBindingUtil.setContentView(this, R.layout.activity_dogs_list)
        binding.viewModel = listViewModel
        setAdapter(binding)
        binding.viewModel?.getDogs()
    }


    private fun setAdapter(binding: ActivityDogsListBinding) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.paymentRecyclerView.layoutManager = layoutManager
        listViewModel.postListAdapter.addOnCLickItemListener(this)
        listViewModel.addMessageReceiver(this)
    }


    private fun initViewModel() {
        listViewModel = ViewModelProviders.of(this).get(DogsListViewModel::class.java)
        listViewModel.postListAdapter
    }


}
