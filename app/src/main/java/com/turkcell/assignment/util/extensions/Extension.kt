package com.turkcell.assignment.util.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.let {
        it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
    }
    return result
}

fun Fragment.showMessage(string: String?) {
    activity?.let { activity -> string?.let { Snackbar.make(activity.findViewById(android.R.id.content), string, Snackbar.LENGTH_LONG).show() } }
}