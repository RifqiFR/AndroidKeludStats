package com.keludstats.shared.modul.inputinfografi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.keludstats.R
import com.keludstats.databinding.InfografisUploadActivityBinding
import com.keludstats.shared.modul.LoadingDialog
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast
import java.io.File


abstract class InputInfografiActivity : AppCompatActivity(), InputInfografiContract.View {
    protected lateinit var binding : InfografisUploadActivityBinding
    private var loadingDialog : LoadingDialog? = null

    companion object {
        private const val PICK_PHOTO_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.infografis_upload_activity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PICK_PHOTO_REQUEST_CODE -> {
                try {
                    data?.data?.let {
                        getRealPathFromURI(it)?.let {
                            Log.d(TAG, "Choosing photo : $it")
                            binding.inputInfografi?.picture = File(it)
                            showLogoFromGallery()
                        }
                    }
                } catch (e: Exception) {
                    Log.w(TAG, e.toString())
                }
            }
        }
    }

    override fun showLogoFromGallery() {
        binding.inputInfografi?.picture?.let {
            val myBitmap = BitmapFactory.decodeFile(it.absolutePath)

            binding.inputInfografiIv.setImageBitmap(myBitmap)
        }
    }

    override fun pickLogoFromGallery() {
        //ask permission, then open gallery
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        Log.i(TAG, "Storage permission granted")
                        openGallery()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                    ) {
                        Log.i(TAG, "Storage permission is denied permanently")
                        //TODO: Berikan pesan error yg menjelaskan bahwa storage diperlukan
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Log.i(TAG, "Storage permission denied")
                    }
                })
                .check();
    }

    // And to convert the image URI to the direct file system path of the image file
    private fun getRealPathFromURI(contentUri: Uri): String? {
        // can post image
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(contentUri,
                proj,  // Which columns to return
                null,  // WHERE clause; which rows to return (all rows)
                null,  // WHERE clause selection arguments (none)
                null) // Order-by clause (ascending by name)
        val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return cursor?.getString(columnIndex!!)
    }

    override fun startLoading() {
        if(loadingDialog == null)
            loadingDialog = LoadingDialog()

        loadingDialog?.show(supportFragmentManager, TAG)
    }

    override fun stopLoading() {
        loadingDialog?.dismiss()
    }

    override fun showEveryFieldIsMandatoryErrorMessage() {
        showToast(getString(R.string.every_field_is_mandatory))
    }

    private fun openGallery() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val mimeType = arrayOf(
                "image/jpeg",
                "image/png",
                "image/svg",
                "image/jpg"
        )

        pickIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        startActivityForResult(pickIntent, PICK_PHOTO_REQUEST_CODE)
    }
}