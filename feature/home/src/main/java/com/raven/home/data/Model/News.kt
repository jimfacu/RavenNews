package com.raven.home.data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(val title:String,val byline:String,val published_date:String,val abstract:String): Parcelable {
}