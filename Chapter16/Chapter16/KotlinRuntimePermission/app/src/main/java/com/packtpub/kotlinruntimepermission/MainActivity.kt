package com.packtpub.kotlinruntimepermission

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

private const val REQUEST_PERMISSION = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun checkPermission(permission: String): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(this, permission)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permissionName: String, permissionRequestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayOf(permissionName),
                permissionRequestCode)
    }

    private fun showExplanation(title: String, message: String,
                                permission: String,
                                permissionRequestCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok
                ) { dialog, id -> requestPermission(permission, permissionRequestCode) }
        builder.create().show()
    }

    fun doSomething(view: View) {
        if (!checkPermission(Manifest.permission.SEND_SMS)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
                showExplanation("Permission Needed", "Rationale",
                        Manifest.permission.SEND_SMS, REQUEST_PERMISSION)
            } else {
                requestPermission(Manifest.permission.SEND_SMS,
                        REQUEST_PERMISSION)
            }
        } else {
            Toast.makeText(this@MainActivity, "Permission (already) Granted!", Toast.LENGTH_SHORT)
                    .show()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity, "Granted!", Toast.LENGTH_SHORT)
                            .show()
                } else {
                    Toast.makeText(this@MainActivity, "Denied!", Toast.LENGTH_SHORT)
                            .show()
                }
                return
            }
        }
    }
}
