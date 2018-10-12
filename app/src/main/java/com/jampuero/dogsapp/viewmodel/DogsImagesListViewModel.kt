package com.jampuero.dogsapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.jampuero.dogsapp.DogsApplication
import com.jampuero.dogsapp.R
import com.jampuero.dogsapp.models.DogApiResponseModel
import com.jampuero.dogsapp.remote.CustomObserver
import com.jampuero.dogsapp.remote.DogsApi
import com.jampuero.dogsapp.ui.Messages
import com.jampuero.dogsapp.utils.NetworkConnection
import com.jampuero.dogsapp.ui.adapters.DogsImagesListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsImagesListViewModel : ViewModel() {

    val postListAdapter: DogsImagesListAdapter = DogsImagesListAdapter()
    lateinit var disposable: Disposable
    val loadingVisibility = ObservableBoolean(false)
    fun getAdapter(): DogsImagesListAdapter {
        return postListAdapter
    }

    private var messages: Messages?=null

    fun addMessageReceiver(messages: Messages) {
        this.messages = messages
    }
    fun getDogBreedsImages( breed:String) {
        loadingVisibility.set(true)
        if (NetworkConnection().isNetworkOnline()) {
            DogsApi().paymentApiInstance.getBreedsImages(breed)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CustomObserver<DogApiResponseModel<List<String>>>() {
                        override fun onSuccess(response: DogApiResponseModel<List<String>>) {
                            response?.message?.let { postListAdapter.setFirstData(it) }
                        }

                        override fun onComplete() {
                            loadingVisibility.set(false)
                        }

                        override fun onSubscribe(d: Disposable) {
                            disposable = d
                        }

                        override fun onError(e: Throwable) {
                            messages?.showSnackBarMessage(DogsApplication.applicationContext().getString(R.string.error))
                        }

                    })
        } else {
            loadingVisibility.set(false)
            messages?.showSnackBarMessage(DogsApplication.applicationContext().getString(R.string.no_internet))
        }
    }
    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

}