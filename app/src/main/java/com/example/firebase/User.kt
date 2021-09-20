package com.example.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val firstName: String? = null, val lastName: String? = null, val number: String? = null) : Parcelable


