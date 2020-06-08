package com.turkcell.assignment.util.bindingadapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.turkcell.assignment.R

@BindingAdapter(value = ["loadWithUrl"], requireAll = false)
fun ImageView.loadWithUrl(url: String?) {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.colorPrimary).error(R.color.colorAccent)
    Glide.with(this.context).load(url).centerCrop().apply(requestOptions).into(this)
}

@BindingAdapter("viewState", requireAll = false)
fun View.setViewState(state: Boolean) = when (state) {
    true -> this.visibility = View.VISIBLE
    false -> this.visibility = View.GONE
}