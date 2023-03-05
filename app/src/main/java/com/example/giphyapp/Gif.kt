package com.example.giphyapp

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: Images
) : Parcelable {

    // Реализация интерфейса Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeParcelable(images, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Конструктор, используемый для создания объекта Gif из Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable<Images>(Images::class.java.classLoader)!!
    )

    companion object CREATOR : Parcelable.Creator<Gif> {
        override fun createFromParcel(parcel: Parcel): Gif {
            return Gif(parcel)
        }

        override fun newArray(size: Int): Array<Gif?> {
            return arrayOfNulls(size)
        }
    }
}

data class Images(
    @SerializedName("fixed_width") val fixedWidth: FixedWidth
) : Parcelable {

    // Реализация интерфейса Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(fixedWidth, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Конструктор, используемый для создания объекта Images из Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readParcelable<FixedWidth>(FixedWidth::class.java.classLoader)!!
    )

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}

data class FixedWidth(
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: String?,
    @SerializedName("height")
    val height: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(width)
        parcel.writeString(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FixedWidth> {
        override fun createFromParcel(parcel: Parcel): FixedWidth {
            return FixedWidth(parcel)
        }

        override fun newArray(size: Int): Array<FixedWidth?> {
            return arrayOfNulls(size)
        }
    }
}



