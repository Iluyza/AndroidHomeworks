package ru.itis.persikill.androidhomeworks

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var image_rui: Uri? = null
    var pickedBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun pickPhoto(view: View) {
        if ((ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ) || ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
                    ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA),
                1
            )
        } else {
            val galeriIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent, 2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galeryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeryIntent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            image_rui = data.data
            if (image_rui != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver, image_rui!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    binding.ivAvatar.setImageBitmap(pickedBitMap)
                } else {
                    pickedBitMap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, image_rui)
                    binding.ivAvatar.setImageBitmap(pickedBitMap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }



////        with(binding) {
////            btnCameraPermission.setOnClickListener {
////                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
////                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
////                    val permission = arrayListOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
////                    requestPermissions(permission,PERMISSION_CODE)
////                }
////                else {
////                    openCamera()
////                }
////            }
////        }
//    }
//    private val ff = registerForActivityResult(SecondActivityContract()){
//        if(it == null) {
//            binding.ivAvatar.setImageURI(image_rui)
//        } else {
//            binding.ivAvatar.setImageURI(it)
//        }
//    }
//
//
//    private val activityForResult: ActivityResultLauncher<Intent> =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            binding.ivAvatar.setImageURI(result.data?.data)
//            this.onActivityResult(
//                IMAGE_CAPTURE_CODE,
//                result.resultCode,
//                result.data
//            )
//        }
//
//
//
//    private fun openCamera() {
//        val values = ContentValues()
//        values.put(MediaStore.Images.Media.TITLE, "New Picture")
//        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera")
//        image_rui = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
//
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_rui)
//        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE)
//
//    }
////
////    override fun onRequestPermissionsResult(
////        requestCode: Int,
////        permissions: Array<out String>,
////        grantResults: IntArray
////    ) {
////        when(requestCode) {
////            PERMISSION_CODE -> {
////                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                    openCamera()
////                } else {
////                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
////                }
////            }
////        }
////    }
//
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        if (resultCode == Activity.RESULT_OK) {
////            binding.ivAvatar.setImageURI(image_rui)
////        }
////    }
//
//    //    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
////    }
//
////    val camera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
////        it?.let {
////            binding.ivAvatar = it.
////        }
////    }
//
//    private val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) {
//        if (it) binding.ivAvatar.setImageURI(image_rui)
//    }
//    private val mPermissions =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
//
}

