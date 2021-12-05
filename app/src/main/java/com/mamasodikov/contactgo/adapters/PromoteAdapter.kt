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
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.mamasodikov.contactgo.databinding.PromoCardsItemBinding


class PromoteAdapter (var contacts: List<Contact>): RecyclerView.Adapter <PromoteAdapter.ItemHolder>() {
    inner class ItemHolder(var bnd: PromoCardsItemBinding) : RecyclerView.ViewHolder(bnd.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = PromoCardsItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(binding)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.bnd.progress.setProgress(position*10+50)

        holder.bnd.fullName.setText("${contacts[position].name} ${contacts[position].sname}")
        holder.bnd.description.setText(contacts[position].desc)
        holder.bnd.phoneNum.setText(contacts[position].phone)
        holder.bnd.socTel.setText(contacts[position].socTel)
        holder.bnd.socFac.setText(contacts[position].socFac)
        holder.bnd.socIns.setText(contacts[position].socIns)

    }


    override fun getItemCount(): Int {
        return contacts.size
    }
}