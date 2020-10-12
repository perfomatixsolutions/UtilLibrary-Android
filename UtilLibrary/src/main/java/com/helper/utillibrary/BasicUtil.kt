package com.helper.utillibrary

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern


public class BasicUtil {
    fun showToastShort(ctx: Context?, msg: CharSequence?) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(ctx: Context?, msg: CharSequence?, duration: Int): Toast? {
        val toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT)
        toast.duration = duration
        toast.show()
        return toast
    }

    fun isNetworkAvailable(ctx: Context): Boolean {
        val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun getDataConnectionType(ctx: Context): String {

        // use application level context to avoid unnecessary leaks.
        val connectivityManager = ctx.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (connectivityManager?.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE
            ) != null
        ) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnected) {
                return "Mobile Network"
            } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!
                    .isConnected
            ) {
                return "Wifi Network"
            } else {
                return "No network"
            }
        } else {
            return "No network"
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun isValidEmail(email: String?): Boolean {
        if (email == null) {
            return false
        }
        val emailPattern =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val matcher: Matcher
        val pattern: Pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(email)
        return matcher?.matches() ?: false
    }

    fun getAppVersion(mContext: Context): String? {
        var existingVersion: String? = null

        try {
            val manager: PackageManager = mContext.getPackageManager()
            val info: PackageInfo
            info = manager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES)
            existingVersion = info.versionName
           return existingVersion
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return existingVersion

        }
    }

}