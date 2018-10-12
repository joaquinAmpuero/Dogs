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
import com.jampuero.dogsapp.ui.adapters.DogsListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsListViewModel : ViewModel() {
    val postListAdapter: DogsListAdapter = DogsListAdapter()
    val loadingVisibility = ObservableBoolean(false)
    lateinit var disposable: Disposable


    fun getAdapter(): DogsListAdapter {
        return postListAdapter
    }

    private var messages: Messages? = null

    fun addMessageReceiver(messages: Messages) {
        this.messages = messages
    }

    fun getDogs() {
        loadingVisibility.set(true)
        if (NetworkConnection().isNetworkOnline()) {
            DogsApi().paymentApiInstance.getAllDogBreeds()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CustomObserver<DogApiResponseModel<HashMap<String, List<String>>>>() {
                        override fun onError(e: Throwable) {
                            loadingVisibility.set(false)
                            messages?.showSnackBarMessage(DogsApplication.applicationContext().getString(R.string.error))
                        }

                        override fun onSuccess(dogApiResponseModel: DogApiResponseModel<HashMap<String, List<String>>>) {
                            getAdapter().setFirstData(dogApiResponseModel.message!!)
                            loadingVisibility.set(false)
                        }


                        override fun onComplete() {
                            loadingVisibility.set(false)
                        }

                        override fun onSubscribe(d: Disposable) {
                            disposable = d
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
            loadingVisibility.set(false)
        }
    }

}