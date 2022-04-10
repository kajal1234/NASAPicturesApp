package com.obvious.nasapicturresapp.repository.model

import android.os.Parcel
import android.os.Parcelable

data class NasaPictureModel(
    public var copyright:String? = "",
    public var date:String? = "",
    public var explanation:String? = "",
    public var hdurl:String? = "",
    public var media_type:String? = "",
    public var service_version:String? = "",
    public var title:String? = "",
    public var url:String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(copyright)
        parcel.writeString(date)
        parcel.writeString(explanation)
        parcel.writeString(hdurl)
        parcel.writeString(media_type)
        parcel.writeString(service_version)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NasaPictureModel> {
        override fun createFromParcel(parcel: Parcel): NasaPictureModel {
            return NasaPictureModel(parcel)
        }

        override fun newArray(size: Int): Array<NasaPictureModel?> {
            return arrayOfNulls(size)
        }
    }
}