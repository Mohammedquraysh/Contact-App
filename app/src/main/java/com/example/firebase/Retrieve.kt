package com.example.firebase

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize


/** this is the data class that save the data pass into the database*/
@Parcelize
data class Retrieve(var id: String? = null, var firstName: String? = null, var lastName: String? = null, var number: String? = null) : Parcelable


