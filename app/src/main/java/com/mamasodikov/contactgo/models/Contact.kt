package com.mamasodikov.contactgo.models

import android.os.Parcel
import android.os.Parcelable

data class Contact(
    var contact_id: String? = "",
    var name:String? = "",
    var sname: String? = "",
    var desc:String? = "",
    var phone:String? = "",
    var socTel:String? = "",
    var socFac:String? = "",
    var socIns:String? = "",
    var owner:String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contact_id)
        parcel.writeString(name)
        parcel.writeString(sname)
        parcel.writeString(desc)
        parcel.writeString(phone)
        parcel.writeString(socTel)
        parcel.writeString(socFac)
        parcel.writeString(socIns)
        parcel.writeString(owner)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}
