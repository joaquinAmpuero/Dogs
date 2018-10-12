package com.jampuero.dogsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jampuero.dogsapp.DogsApplication

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class NetworkConnection {
    fun isNetworkOnline(): Boolean {
        var status = false
        if (DogsApplication.applicationContext() != null) {
            val cm = DogsApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    status = true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    status = true
                } else if (activeNetwork.isConnected) {
                    status = true
                }
            } else {
                // not connected to the internet
                status = false
            }
        }
        return status

    }

}