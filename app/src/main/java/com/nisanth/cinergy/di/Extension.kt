package com.nisanth.cinergy.di

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.nisanth.cinergy.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

/**
 * Will block the entire screen, for restricting multiple clicks
 **/
fun ProgressBar.blockUI(activity: Activity) {
    this.visibility = View.VISIBLE
    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun ProgressBar.unBlockUI(activity: Activity) {
    this.visibility = View.GONE
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun String.isSuccess() = this == "success"

fun Activity.initToolbar(tbar: Toolbar, name: String, navController: NavController) {
    (this as AppCompatActivity).setSupportActionBar(tbar)
    this.supportActionBar?.apply {
        title = name
        setDisplayHomeAsUpEnabled(true)
    }
    tbar.setNavigationOnClickListener {
        navController.navigateUp()
    }
}

fun Context.loadImageFromUrl(imageView: ImageView, url: String?) {
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.ic_movies)
        .into(imageView)
}

fun formatDate(dateToFormat: String): Pair<String, String> {
    val inFormat = SimpleDateFormat("yyyy-MM-dd")
    var date: Date? = null
    try {
        date = inFormat.parse(dateToFormat)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val outFormat1 = SimpleDateFormat("EEE")
    val outFormat2 = SimpleDateFormat("MMM yy")

    return Pair("${outFormat1.format(date)}\n", outFormat2.format(date))
}

fun View.getCompatColor(@ColorRes colorRes: Int): Int =
    ContextCompat.getColor(this.context, colorRes)