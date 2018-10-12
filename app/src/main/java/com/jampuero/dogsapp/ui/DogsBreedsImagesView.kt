package com.jampuero.dogsapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.jampuero.dogsapp.R
import com.jampuero.dogsapp.databinding.ActivityDogsBreedsImagesListBinding
import com.jampuero.dogsapp.viewmodel.DogsImagesListViewModel

class DogsBreedsImagesView : AppCompatActivity(), Messages {
    override fun showSnackBarMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
    }


    private lateinit var listViewModel: DogsImagesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        val binding: ActivityDogsBreedsImagesListBinding = DataBindingUtil.setContentView(this, R.layout.activity_dogs_breeds_images_list)
        binding.viewModel = listViewModel
        setAdapter(binding)
        listViewModel.getDogBreedsImages(intent.extras!!.getString("breed", ""))
    }

    private fun setAdapter(binding: ActivityDogsBreedsImagesListBinding) {
        val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.paymentRecyclerView.layoutManager = layoutManager
    }


    private fun initViewModel() {
        listViewModel = ViewModelProviders.of(this).get(DogsImagesListViewModel::class.java)
        listViewModel.postListAdapter
        listViewModel.addMessageReceiver(this)

    }


}
