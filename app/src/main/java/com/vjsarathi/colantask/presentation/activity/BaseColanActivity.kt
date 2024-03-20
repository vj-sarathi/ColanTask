package com.vjsarathi.colantask.presentation.activity

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseColanActivity : AppCompatActivity() {

    fun showSnackBar(view: View, @StringRes messageResId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, messageResId, duration).show()
    }
    fun showSnackBar(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, message, duration).show()
    }

}