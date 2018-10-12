package com.jampuero.dogsapp.binding

import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.ViewTarget
import com.jampuero.dogsapp.R

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
object CustomBindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
                .load(url)
                /*.placeholder ( resourcePlaceholderDrawable )*/
                .crossFade()
                .error(R.drawable.abc_btn_check_material)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : ViewTarget<ImageView, GlideDrawable>(imageView) {
                    override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                        imageView.setImageDrawable(resource)
                    }

                })
    }

    @BindingAdapter(value = ["mutableVisibility"], requireAll = false)
    @JvmStatic
    fun View.setMutableVisibility(boolean: ObservableBoolean?) {
        boolean?.get()?.let {
            visibility = if (it) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, setFillAfter: Boolean, startValue: Float, finalValue: Float) {
        val alphaAnimation = AlphaAnimation(startValue, finalValue)
        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = setFillAfter
        v.startAnimation(alphaAnimation)
    }

}