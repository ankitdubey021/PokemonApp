package com.ankitdubey.pokemonapp.utils

/**
 * Created by Ankit Dubey on 13,November,2021
 */


import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.ankitdubey.pokemonapp.R

class ProgressBarUtils {
    companion object {
        protected var mProgressDialog: Dialog? = null

        fun showProgressDialog(
            context: Context?
        ) {
            if (context == null){
                return
            }
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                return
            }
            mProgressDialog = Dialog(context)
            try {
                mProgressDialog!!.window?.requestFeature(Window.FEATURE_NO_TITLE)
                mProgressDialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
                val view = LayoutInflater.from(context).inflate(R.layout.layout_circular_loader,null,false)
                mProgressDialog!!.setContentView(view)
                mProgressDialog!!.setCancelable(false)
                mProgressDialog!!.show()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun removeProgressDialog() {
            try {
                if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                    mProgressDialog!!.dismiss()
                    mProgressDialog = null
                }
            } catch (e: Exception) {
            }
        }
    }
}