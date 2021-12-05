package com.mamasodikov.contactgo.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.databinding.SharedCardsItemBinding
import com.mamasodikov.contactgo.models.Contact


class SharedCardsAdapter (var contacts: List<Contact>,val context: Context): RecyclerView.Adapter <SharedCardsAdapter.ItemHolder>() {
    inner class ItemHolder(var bnd: SharedCardsItemBinding) : RecyclerView.ViewHolder(bnd.root)

    val pref: SharedPreferences = context.getSharedPreferences("MyPref",
        AppCompatActivity.MODE_PRIVATE
    );
    val currentUser: String? = pref.getString("current_user","user1")
    val database: FirebaseDatabase = Firebase.database
    val myReference: DatabaseReference = database.getReference("users")

    var Temp:Contact=Contact(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = SharedCardsItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, @SuppressLint("RecyclerView") position: Int) {
        val fullName:String="${contacts[position].name} ${contacts[position].sname}"
        holder.bnd.fullName.setText(fullName)
        holder.bnd.description.setText(contacts[position].desc)
        holder.bnd.phoneNum.setText(contacts[position].phone)
        holder.bnd.socTel.setText(contacts[position].socTel)
        holder.bnd.socFac.setText(contacts[position].socFac)
        holder.bnd.socIns.setText(contacts[position].socIns)
        
        holder.bnd.qrcode.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(context, "Muallif kartaga bo'lishish huquqini bermagan", Toast.LENGTH_SHORT).show()
            }
        })

        holder.bnd.sharedCardsCard.setOnLongClickListener(object: View.OnLongClickListener{
            @RequiresApi(Build.VERSION_CODES.Q)
            @SuppressLint("NotifyDataSetChanged")
            override fun onLongClick(p0: View?): Boolean {

                Temp=contacts[position]

                val builder = AlertDialog.Builder(context)
                builder.setMessage("Rostdan ham o'chirmoqchimisiz?")
                    .setCancelable(false)
                    .setPositiveButton("Ha") { dialog, id ->
                        // Bazadan o'chirish

                        myReference.child(currentUser!!).child("shared").child(Temp.contact_id.toString()).removeValue()
                        vibrate()
                    }
                    .setNegativeButton("Yo'q") { dialog, id ->
                        // Bekor qilish
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

                return true
            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun vibrate(){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            vibrator.vibrate(20)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}