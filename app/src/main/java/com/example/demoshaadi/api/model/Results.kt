package com.example.demoshaadi.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(val gender:String?, val email:String?, val phone:String?, val cell:String?, val nat:String?,
                   val name: Name?, val login: Login, val dob: Dob, val registered: Dob, val id: Id,
                   val picture: Picture, val location: Location
):Parcelable {
    @Parcelize
    data class Name(val title:String,val first:String,val last:String):Parcelable
    @Parcelize
    data class Login(val username:String,val password:String):Parcelable
    @Parcelize
    data class Dob(val date:String,val age:Int):Parcelable
    @Parcelize
    data class Id(val name:String,val value:String?):Parcelable
    @Parcelize
    data class Picture(val large:String,val medium:String,val thumbnail:String):Parcelable
}