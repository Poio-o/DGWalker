package com.alanturing.dgwalker.core.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionManager {

    fun getRequiredPermissions(): Array<String> {
        return mutableListOf<String>().apply {
            add(Manifest.permission.ACTIVITY_RECOGNITION)
            add(Manifest.permission.POST_NOTIFICATIONS)
        }.toTypedArray()
    }

    fun hasAllPermissions(context: Context): Boolean {
        return getRequiredPermissions().all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissions(launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>) {
        val permissions = getRequiredPermissions()
        if (permissions.isNotEmpty()) {
            launcher.launch(permissions)
        }
    }
}
