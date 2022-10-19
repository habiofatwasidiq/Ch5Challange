package com.example.ch4challange.model

import android.os.Parcel
import android.os.Parcelable




data class SliderData(
    val description: String?,
    val imgSlider: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(imgSlider)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SliderData> {
        override fun createFromParcel(parcel: Parcel): SliderData {
            return SliderData(parcel)
        }

        override fun newArray(size: Int): Array<SliderData?> {
            return arrayOfNulls(size)
        }
    }
}

