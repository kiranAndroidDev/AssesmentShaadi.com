package com.example.demoshaadi.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(val street:String?,val city:String?,val state:String?,val postcode:String?,val timezone: Timezone):Parcelable {

    @Parcelize
    class Timezone(val offset:String,val description:String):Parcelable
}