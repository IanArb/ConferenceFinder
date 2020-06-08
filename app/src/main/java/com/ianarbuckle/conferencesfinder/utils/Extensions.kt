package com.ianarbuckle.conferencesfinder.utils

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ianarbuckle.conferencesfinder.R

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun ImageView.provideImage(context: Context, imageUrl: String) {
    val requestOptions = RequestOptions()
    requestOptions.apply {
        error(R.drawable.london)
    }

    Glide.with(context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(this)
}