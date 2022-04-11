package com.obvious.nasapicturresapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Developed by Kajal Kukdeja on 10,April,2022
 * Data class to match with api response
 */

data class NasaPictureModel(

    @SerializedName("copyright")
    @Expose
    var copyright: String? = "",

    @SerializedName("date")
    @Expose
    var date: String? = "",

    @SerializedName("explanation")
    @Expose
    var explanation: String? = "",

    @SerializedName("hdurl")
    @Expose
    var hdurl: String? = "",

    @SerializedName("media_type")
    @Expose
    var media_type: String? = "",

    @SerializedName("service_version")
    @Expose
    var service_version: String? = "",

    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("url")
    @Expose
    var url: String? = ""
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