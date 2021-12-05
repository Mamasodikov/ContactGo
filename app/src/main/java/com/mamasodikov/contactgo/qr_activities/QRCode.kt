package com.mamasodikov.contactgo.qr_activities

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.mamasodikov.contactgo.adapters.EXTRA_TEMP
import com.mamasodikov.contactgo.databinding.ActivityQrcodeBinding
import com.mamasodikov.contactgo.models.Contact

class QRCode : AppCompatActivity() {

    lateinit var b: ActivityQrcodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityQrcodeBinding.inflate(layoutInflater)
        setContentView(b.root)

        var ID:String=""
        val writer = QRCodeWriter()
        val Temp: Contact? = intent.getParcelableExtra<Contact>(EXTRA_TEMP)
        if (Temp != null)
        {
            ID= Temp.contact_id.toString()

            val bitMatrix = writer.encode(ID,BarcodeFormat.QR_CODE,512,512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for(x in 0 until width){
                for (y in 0 until width)
                {
                    bmp.set(x,y, if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }

            b.QRImage.setImageBitmap(bmp)
        }



    }
}