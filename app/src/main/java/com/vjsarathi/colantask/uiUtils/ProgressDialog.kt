package com.vjsarathi.colantask.uiUtils

import android.content.Context
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog

object ProgressDialog {

    private var dialog: AlertDialog? = null

    fun show(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        val progressBar = ProgressBar(context)
        builder.setView(progressBar)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}