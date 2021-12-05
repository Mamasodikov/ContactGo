package com.mamasodikov.contactgo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mamasodikov.contactgo.databinding.GovCardsItemBinding
import com.mamasodikov.contactgo.models.BaseTable
import com.mamasodikov.contactgo.models.Tashkilot

class GovAdapter(var contacts: List<BaseTable>, var context: Context, var color:String): RecyclerView.Adapter <GovAdapter.ItemHolder>() {
    inner class ItemHolder(var bnd: GovCardsItemBinding) : RecyclerView.ViewHolder(bnd.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = GovCardsItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {


        if (Build.VERSION.SDK_INT <29) {
            val bg: Drawable = holder.bnd.header.getBackground()
            bg.setColorFilter(Color.parseColor(color), PorterDuff.Mode.ADD)
        }
        else
        {
            holder.bnd.header.background.colorFilter = BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_ATOP)
        }


        holder.bnd.fullName.setText(contacts[position].tableColumn.FISH.text.uzbText)
        holder.bnd.description.setText("Tashkilot rahbari")
        holder.bnd.phoneNum.setText(contacts[position].tableColumn.Telefon1.default)
        holder.bnd.govName.setText(contacts[position].tableColumn.MUASSASALARNOMI.text.uzbText)
        holder.bnd.address.setText(contacts[position].tableColumn.MANZIL.text.uzbText)
        holder.bnd.email.setText(contacts[position].tableColumn.mail.default)
        holder.bnd.website.setText(contacts[position].tableColumn.Websayt.default)

        holder.bnd.bookmark.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(context, "Test vizitkani saqlab ololmaysiz", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun getItemCount(): Int {
        return contacts.size
    }
}