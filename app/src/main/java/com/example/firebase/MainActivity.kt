package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    /** this is a variable that send the user details to database*/
        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        /** this is the button the take save all the user details*/
        binding.btnLogin.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val number = binding.number.text.toString()
            val user = Retrieve(firstName = firstName,lastName = lastName,number = number)
            user.id = dataBase.push().key
//            Log.d("Firebase", user.id!!)


               if(firstName.isNotEmpty() || lastName.isNotEmpty() || number.isNotEmpty()){
                  //// result = true
                   dataBase.child(user.id!!).setValue(user).addOnSuccessListener {
                       Log.d("User", user.toString())
                       binding.firstName.text?.clear()
                       binding.lastName.text?.clear()
                       binding.number.text?.clear()

                       Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()
                       val intent = Intent(this, UserActivity::class.java)
                       intent.putExtra("contact", user.firstName)
                       startActivity(intent)

                   }.addOnFailureListener {
                       Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
                   }


               }else{
//
                   Toast.makeText(this, "field can not be empty", Toast.LENGTH_SHORT).show()
               }
        }
    }




}