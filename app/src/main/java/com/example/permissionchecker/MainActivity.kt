package com.example.permissionchecker

import android.Manifest
import android.os.Bundle
import com.ariidjs.permissionchecker.PermissionChecker

class MainActivity : PermissionChecker(), PermissionChecker.PermissionCallback {
    companion object {
        const val PERMISSION_REQUEST_CODE = 10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            requestCode = PERMISSION_REQUEST_CODE
        )
    }

    override fun onPermissionResult(requestCode: Int, type: Type) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            when (type) {
                Type.GRANTED -> {} //do the action when granted
                Type.DENIED -> {} // do the action when denied :)
            }
        }
    }

}