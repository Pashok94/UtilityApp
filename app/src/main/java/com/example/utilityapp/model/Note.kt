package com.example.utilityapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(val title: String, val description: String, val date: Long) : Parcelable