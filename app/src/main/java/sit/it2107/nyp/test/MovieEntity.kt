package sit.it2107.nyp.test

import android.os.Parcelable
import android.graphics.Movie
import android.os.Parcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieEntity (
    val title: String,
    val desc: String,
    val language: String,
    val date: String,
    val suitable: String,
    val review1: String = "No Reviews yet.",
    val review2: String = "Long press here to add") : Parcelable {

    companion object {
        @JvmField
        val Parceler = object : Parcelable.Creator<MovieEntity> {
            override fun createFromParcel(parcel: Parcel) = MovieEntity(parcel)
            override fun newArray(size: Int) = arrayOfNulls<MovieEntity>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        desc = parcel.readString(),
        language = parcel.readString(),
        date = parcel.readString(),
        suitable = parcel.readString(),
        review1 = parcel.readString(),
        review2 = parcel.readString()
    )

    //   override fun writeToParcel(parcel: Parcel, flags: Int) {
    //      parcel.writeString(title)
    //     parcel.writeString(desc)
    //      parcel.writeString(language)
    //      parcel.writeString(date)
    //      parcel.writeString(suitable)
    //  }

    override fun describeContents() = 0

    override fun toString(): String {
        return "MovieEntity(title='$title', desc='$desc', language='$language', date='$date', suitable='$suitable', review1='$review1', review2='$review2')"
    }
}