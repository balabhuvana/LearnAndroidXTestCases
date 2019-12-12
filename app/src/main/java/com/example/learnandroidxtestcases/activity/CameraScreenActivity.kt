package com.example.learnandroidxtestcases.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_camera_screen.*

class CameraScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_screen)

        btnStartCamera.setOnClickListener {
            if (checkCameraPermission()) {

                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.provide_camera_permission),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA_CODE) {
            val extras = data!!.extras
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return
            }
            val bitmap = extras!!.get(KEY_IMAGE_DATA) as Bitmap
            ivCaptureImage.setImageBitmap(bitmap)
        }
    }

    companion object {
        var REQUEST_CAMERA_CODE = 1000
        @VisibleForTesting
        var KEY_IMAGE_DATA = "data"
    }
}

