package com.mamasodikov.contactgo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mamasodikov.contactgo.databinding.SocCardsItemBinding
import com.mamasodikov.contactgo.models.Contact
import android.graphics.drawable.Drawable
import android.graphics.PorterDuff
import android.os.Build


class SocAdapter (var contacts: List<Contact>, var context:Context, var color:String): RecyclerView.Adapter <SocAdapter.ItemHolder>() {
        inner class ItemHolder(var bnd: SocCardsItemBinding) : RecyclerView.ViewHolder(bnd.root)


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val binding = SocCardsItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            return ItemHolder(binding)
        }

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        override fun onBindViewHolder(holder: ItemHolder, position: Int) {


            if (Build.VERSION.SDK_INT <29) {
                val bg: Drawable = holder.bnd.header.getBackground()
                bg.setColorFilter(Color.parseColor(color), PorterDuff.Mode.ADD)
            }
            else
            {
                holder.bnd.header.background.colorFilter = BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_ATOP)
            }


            holder.bnd.fullName.setText("${contacts[position].name} ${contacts[position].sname}")
            holder.bnd.description.setText(contacts[position].desc)
            holder.bnd.phoneNum.setText(contacts[position].phone)
            holder.bnd.socTel.setText(contacts[position].socTel)
            holder.bnd.socFac.setText(contacts[position].socFac)
            holder.bnd.socIns.setText(contacts[position].socIns)

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