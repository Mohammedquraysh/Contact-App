package com.example.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.database.FirebaseDatabase

class Updating : AppCompatActivity() {
    private lateinit var changeNumbers: TextView
    private lateinit var deleteButton: ImageView
    private lateinit var editButton: ImageView
    private lateinit var shareButton: ImageView
    private lateinit var user: Retrieve
//    private lateinit var dataBase:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val backArrowButton = findViewById<ImageView>(R.id.imageView6)
        changeNumbers = findViewById(R.id.number)
        val callButton = findViewById<ImageView>(R.id.ivImage)
        var firstName : TextView = findViewById(R.id.fName)
        var lastName: TextView = findViewById(R.id.lName)
        var phoneNumber: TextView = findViewById(R.id.number)
        val names = intent.getParcelableExtra<Retrieve>("User")
        user = names!!
//        val lNames = intent.getStringExtra("Last Name")
//        val pNumbers = intent.getStringExtra("phone number")

       firstName.text = names.firstName
        lastName.text = names.lastName
        phoneNumber.text = names.number


/** this is the the button used to share contact */
    shareButton = findViewById(R.id.btn_share)
    shareButton.setOnClickListener {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.ACTION_SEND, "Share ${firstName}, ${lastName}, ${phoneNumber},Contact")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }



/** this is the button to take you to previous activity*/
    backArrowButton.setOnClickListener {
            onBackPressed()
        }


    /** this is the button to delete contact */
        deleteButton =  findViewById(R.id.deletebtn)
        deleteButton.setOnClickListener {
            val id  = user.id
            Log.d("Delete", user.id!!)
            if(id != null){
                deleteContact(id)
            }

        }


/** this is the button that will allow the user to edit and update the contact */
        editButton = findViewById(R.id.edit_button)
        editButton.setOnClickListener {
            val intent = Intent(this, EditContact::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }



     /**  this is the button for making call */
        callButton.setOnClickListener {
            requestPermissionResult()

        }
    }

//
//    private fun shareContact(firstName: String, lastName: String, number: String){
//
//
//
//    }

/** this is the function to delete contacts */
    private fun deleteContact(id: String){
        val  dataBase = FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(id).removeValue().addOnSuccessListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"successful",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }


 /** this is the function that verifies the permission */
    private fun hasCalPhonePermission () =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED



/** this is the function that request for permission*/

    private fun requestPermissionResult(){

        if(!hasCalPhonePermission()){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),0)
        }else{
            callPhone()
        }
    }



/** this is the function that take in intent as a parameter that will allow the user to make call */
    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${changeNumbers.text}"))
        startActivity(intent)
    }



/** this is the function that handles the permission request and check if the request code equal to grant request*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            callPhone()

        }else{
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}