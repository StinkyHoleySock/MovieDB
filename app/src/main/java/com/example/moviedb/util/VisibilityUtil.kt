package com.example.moviedb.util

import android.view.View

fun View.applyVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}