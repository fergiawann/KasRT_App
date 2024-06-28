// Contacts.kt
package com.example.cobafirebase.models

import android.os.Parcel
import android.os.Parcelable

data class Contacts(
    val id: String? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val alamat: String? = null,
    val jabatan: String? = null,
    val imgUri: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
        parcel.writeString(alamat)
        parcel.writeString(jabatan)
        parcel.writeString(imgUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contacts> {
        override fun createFromParcel(parcel: Parcel): Contacts {
            return Contacts(parcel)
        }

        override fun newArray(size: Int): Array<Contacts?> {
            return arrayOfNulls(size)
        }
    }
}
