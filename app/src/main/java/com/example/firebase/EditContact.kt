package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditContact : AppCompatActivity() {
    private lateinit var dataBase: DatabaseReference
    private lateinit var user: Retrieve

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        setContentView(R.layout.activity_to_edit_contact)
        var firstName: EditText = findViewById(R.id.firstname)
        var lastName : EditText = findViewById(R.id.lastname)
        var phoneNumber: EditText = findViewById(R.id.num)
        var saveContactButton: Button = findViewById(R.id.button)
        user = intent.getParcelableExtra("User")!!
        firstName.setText(user.firstName)
        lastName.setText(user.lastName)
        phoneNumber.setText(user.number)


/** this is the button to save contact */
     saveContactButton.setOnClickListener {
          editContact(user.id!!, firstName.text.toString(),lastName.text.toString(),phoneNumber.text.toString())
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }


/** this is the  function to editContact */
    private fun editContact(id: String, firstName: String, lastName: String,  number: String){
        val phoneContact = mapOf<String, String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "number" to number
        )

        val dataBase = FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(id).updateChildren(phoneContact).addOnSuccessListener {
            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
    }

}