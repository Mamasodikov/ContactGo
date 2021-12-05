package com.mamasodikov.contactgo.qr_activities

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.databinding.ActivityScannerBinding

class Scanner : AppCompatActivity() {

    lateinit var b: ActivityScannerBinding
    private lateinit var codescanner:CodeScanner

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(b.root)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA), 123)
        }
        else
        {
            startScanning()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun startScanning() {
        codescanner = CodeScanner(this, b.scannerView)
        codescanner.camera = CodeScanner.CAMERA_BACK
        codescanner.formats = CodeScanner.TWO_DIMENSIONAL_FORMATS
        codescanner.autoFocusMode = AutoFocusMode.SAFE
        codescanner.scanMode = ScanMode.SINGLE
        codescanner.isAutoFocusEnabled = true
        codescanner.isFlashEnabled = false

        codescanner.decodeCallback = DecodeCallback {

            val pref:SharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE)
            val currentUser: String? = pref.getString("current_user","user1")
            val database: FirebaseDatabase = Firebase.database
            val myReference: DatabaseReference = database.getReference("users")
                .child(currentUser!!).child("shared")

            myReference.child(it.text).setValue(it.text)
            vibrate()
            finish()

        }

        codescanner.errorCallback = ErrorCallback {

            runOnUiThread {
                Toast.makeText(this, "Kameraning initsializatsiyasida xato bor | ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        b.scannerView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                codescanner.startPreview()

            }
        })



    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==123)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Kameraga ruxat etildi", Toast.LENGTH_SHORT).show()
            startScanning()
        }
        else
            Toast.makeText(this, "Kameraga ruxsat etilmadi", Toast.LENGTH_SHORT).show()

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun vibrate(){
        val vibrator = this@Scanner.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            vibrator.vibrate(60)
        }
    }

    override fun onResume() {
        super.onResume()
        if(::codescanner.isInitialized)
        {
            codescanner?.startPreview()
        }
    }

    override fun onPause() {
        super.onPause()
        if(::codescanner.isInitialized)
        {
            codescanner?.releaseResources()
        }
    }
}