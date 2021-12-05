package com.mamasodikov.contactgo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Tashkilot(
    @SerializedName("RahbarningFISH", alternate = ["boshqarmaboshligi", "Name", "FISH"]) val FISH:SubCategory,
    @SerializedName("Tashkilotnomi", alternate = ["hududnomi", "Fullname", "MUASSASALARNOMI"]) val MUASSASALARNOMI:SubCategory,
    @SerializedName("Manzil", alternate = ["manzil", "Address", "MANZIL"]) val MANZIL:SubCategory,
    @SerializedName("Telefon", alternate = ["telefon", "TelefonRaqami", "Contactnumber", "Telefon1"]) val Telefon1:SubCategory,
    @SerializedName("Telefon3", alternate = ["Vebsayt", "Veb-sayt", "vebsayt", "Website", "Websayt", "qabulkunlari"]) val Websayt:SubCategory,
    @SerializedName("Elektron pochta manzili", alternate = ["elektronpochtamanzil", "Email", "mail","emanzil"]) val mail:SubCategory)