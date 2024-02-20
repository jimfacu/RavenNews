package com.raven.home.data.Model

import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("status") val status:String,
                        @SerializedName("copyright") val copyright:String,
                        @SerializedName("results") val results:List<News>) {
}