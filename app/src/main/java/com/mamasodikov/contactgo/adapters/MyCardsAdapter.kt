package com.mamasodikov.contactgo.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mamasodikov.contactgo.databinding.MyCardsItemBinding
import com.mamasodikov.contactgo.models.Contact

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.*

import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.Form
import com.mamasodikov.contactgo.qr_activities.QRCode
import com.mamasodikov.contactgo.R


const val EXTRA_TEMP ="EXTRA"
class MyCardsAdapter(var contacts: List<Contact>, val context:Context): RecyclerView.Adapter <MyCardsAdapter.ItemHolder>() {
    inner class ItemHolder(var bnd: MyCardsItemBinding) : RecyclerView.ViewHolder(bnd.root)


    val pref: SharedPreferences = context.getSharedPreferences("MyPref",
        AppCompatActivity.MODE_PRIVATE
    );
    val currentUser: String? = pref.getString("current_user","user1")
    var sharedUser:String=""
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

        val binding = MyCardsItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
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

        holder.bnd.qrcode.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                Temp=contacts[position]
                //Toast.makeText(context, "Passed", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, QRCode::class.java)
                intent.putExtra(EXTRA_TEMP, Temp)
                context.startActivity(intent)

            }
        })

            holder.bnd.myCardsCard.setOnLongClickListener(object: View.OnLongClickListener{
                override fun onLongClick(p0: View?): Boolean {

                    Temp=contacts[position]
                    //Toast.makeText(context, "Passed", Toast.LENGTH_SHORT).show()

                    showDialog()
                    return true
                }
            })

    }

    fun showDialog()
    {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)

        val editLayout: LinearLayout = dialog.findViewById(R.id.layoutEdit)
        val deleteLayout: LinearLayout = dialog.findViewById(R.id.layoutDelete)

//        dialog.setOnDismissListener(object: DialogInterface.OnDismissListener{
//            override fun onDismiss(p0: DialogInterface?) {
//
//            }
//        })

        editLayout.setOnClickListener(object : View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onClick(p0: View?) {
                dialog.dismiss()

                val intent = Intent(context, Form::class.java)
                intent.putExtra(EXTRA_TEMP, Temp)
                context.startActivity(intent)
                vibrate()
            }
        })

        deleteLayout.setOnClickListener(object : View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onClick(p0: View?) {
                 dialog.dismiss()
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Rostdan ham o'chirmoqchimisiz?")
                    .setCancelable(false)
                    .setPositiveButton("Ha") { dialog, id ->
                        // Bazadan o'chirish

                        if(currentUser=="user1")
                        {
                            sharedUser="user2"
                        }
                        else
                        {
                            sharedUser="user1"
                        }
                        myReference.child(currentUser!!).child("cards").child(Temp.contact_id.toString()).removeValue()
                        myReference.child(sharedUser).child("shared").child(Temp.contact_id.toString()).removeValue()
                        vibrate()
                    }
                    .setNegativeButton("Yo'q") { dialog, id ->
                        // Bekor qilish
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        })

        dialog.show()
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.getWindow()!!.getAttributes().windowAnimations = R.style.DialogAnimation
//        dialog.getWindow()?.setGravity(Gravity.BOTTOM)
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