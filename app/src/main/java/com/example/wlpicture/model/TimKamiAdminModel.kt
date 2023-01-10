package com.example.wlpicture.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimKamiAdminModel(
    val id : String,
    val namaLengkap : String,
    val email : String,
    val phone : String,
    val alamat : String,
    val descriptions : String
): Parcelable
