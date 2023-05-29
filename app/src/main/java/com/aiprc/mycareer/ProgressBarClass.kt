package com.aiprc.mycareer

import android.app.ProgressDialog
import android.content.Context

public class ProgressBarClass {
    var progressdialog: ProgressDialog? = null
    fun showProgressBar(context: Context) {
        if (progressdialog != null) {
            progressdialog!!.dismiss()
        } else {
            progressdialog = ProgressDialog(context)
            progressdialog!!.setMessage(context.resources.getString(R.string.please_wait))
            progressdialog!!.setCanceledOnTouchOutside(false)
            progressdialog!!.setCancelable(false)
            progressdialog!!.isIndeterminate = true
            progressdialog!!.show()
        }
    }

    fun stopProgressBar() {
        if (progressdialog != null) progressdialog!!.dismiss()
    }

}