package com.ariidjs.permissionchecker

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

abstract class PermissionChecker : AppCompatActivity() {

    private lateinit var permissionCallback: PermissionCallback

    fun requestPermission(
        callback: PermissionCallback,
        vararg permissions: String,
        requestCode: Int
    ) {
        permissionCallback = callback
        if (!hasPermissions(*permissions)) {
            if (shouldShownRationale(*permissions)) {
                showPermissionSnackBar(
                    R.string.permission_rationale,
                    android.R.string.ok
                ) {
                    ActivityCompat.requestPermissions(this, permissions, requestCode)
                }
            } else {
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        } else {
            permissionCallback.onPermissionResult(requestCode, Type.DENIED)
        }
    }

    fun requestSinglePermission(
        callback: PermissionCallback,
        permission: String,
        requestCode: Int
    ) {
        permissionCallback = callback
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showPermissionSnackBar(
                    R.string.permission_rationale,
                    android.R.string.ok
                ) {
                    ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
                }
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        } else {
            permissionCallback.onPermissionResult(requestCode, Type.DENIED)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (verifyPermissions(grantResults)) {
            permissionCallback.onPermissionResult(requestCode, Type.GRANTED)
        } else {
            if (shouldShownRationale(*permissions)) {
                permissionCallback.onPermissionResult(requestCode, Type.DENIED)
                showPermissionSnackBar(R.string.permission_rationale, android.R.string.ok) {
                    ActivityCompat.requestPermissions(this, permissions, requestCode)
                }
            } else {
                showPermissionSnackBar(
                    R.string.permission_denied_explanation,
                    R.string.settings
                ) {
                    val intent = Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data =
                            Uri.fromParts("package", applicationContext.packageName, null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
                permissionCallback.onPermissionResult(requestCode, Type.DENIED)
            }
        }
    }

    private fun hasPermissions(vararg permissions: String): Boolean {
        for (i in permissions.indices) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != 0) {
                return false
            }
        }
        return true
    }

    private fun shouldShownRationale(vararg permissions: String): Boolean {
        for (i in permissions.indices) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                return true
            }
        }
        return false
    }

    private fun verifyPermissions(grantResults: IntArray): Boolean {
        if (grantResults.isEmpty()) {
            return false
        } else {
            val var3 = grantResults.size
            for (var4 in 0 until var3) {
                val result = grantResults[var4]
                if (result != 0) {
                    return false
                }
            }
            return true
        }
    }

    private fun showPermissionSnackBar(
        snackStrId: Int,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackBar = Snackbar.make(
            findViewById(android.R.id.content),
            getString(snackStrId),
            Snackbar.LENGTH_INDEFINITE
        )
        if (actionStrId != 0 && listener != null) {
            snackBar.setAction(getString(actionStrId), listener)
        }
        snackBar.show()
    }

    interface PermissionCallback {
        fun onPermissionResult(requestCode: Int, type: Type)
    }

    enum class Type {
        GRANTED,
        DENIED
    }
}