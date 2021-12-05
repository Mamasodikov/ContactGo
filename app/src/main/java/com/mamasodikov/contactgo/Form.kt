package com.mamasodikov.contactgo

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.databinding.ActivityFormBinding
import com.mamasodikov.contactgo.models.Contact
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mamasodikov.contactgo.adapters.EXTRA_TEMP


class Form : AppCompatActivity(){

    lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var isEditing:Boolean=false
/************************************/
        val Temp: Contact? = intent.getParcelableExtra<Contact>(EXTRA_TEMP)
        if (Temp != null) {
            binding.nameEdit.setText(Temp.name)
            binding.snameEdit.setText(Temp.sname)
            binding.desc.setText(Temp.desc)
            binding.phoneNum.setText(Temp.phone)
            binding.socTelEdit.setText(Temp.socTel?.replace("t.me/", ""))
            binding.socFacEdit.setText(Temp.socFac?.replace("facebook.com/", ""))
            binding.socInsEdit.setText(Temp.socIns?.replace("instagram.com/", ""))
            isEditing=true
        }
/************************************/


        val pref: SharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        val currentUser: String? = pref.getString("current_user","user1")

        val database:FirebaseDatabase = Firebase.database
        val myReference:DatabaseReference = database.getReference("users").child(currentUser!!)

        binding.submit.setOnClickListener(object: View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onClick(p0: View?) {
                var key:String=""

                if(isEditing)
                {if (Temp != null) {
                        key=Temp.contact_id.toString()
                    }}
                else
                {key= myReference.child("cards").push().key.toString()}

                val contact:Contact = Contact(key,
                    binding.nameEdit.getText().toString(),
                    binding.snameEdit.getText().toString(),
                    binding.desc.getText().toString(),
                    binding.phoneNum.getText().toString(),
                    "t.me/"+binding.socTelEdit.getText().toString(),
                    "facebook.com/"+binding.socFacEdit.getText().toString(),
                    "instagram.com/"+binding.socInsEdit.getText().toString(),
                    currentUser)

                    myReference.child("cards").child(key)
                        .setValue(contact)
                Toast.makeText(this@Form, "Vizitkangiz qo'shildi", Toast.LENGTH_SHORT).show()
                vibrate()
                finish()
            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun vibrate(){
        val vibrator = this@Form.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            vibrator.vibrate(20)
        }
    }

}