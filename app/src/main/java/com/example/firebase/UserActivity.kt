package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import java.util.ArrayList

class UserActivity : AppCompatActivity() , MyAdapter.OnItemClickListener{
    lateinit var myArrayList: ArrayList<User>

    private lateinit var userRecyclerview: RecyclerView
    private lateinit var retrieveList: ArrayList<Retrieve>
    lateinit var firstName: Array<String>
    lateinit var lastName: Array<String>
    private lateinit var dataBase: DatabaseReference
    private lateinit var adapterNew: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

      //  val contactName = intent.getStringExtra("contact")

        dataBase = FirebaseDatabase.getInstance().getReference("Users")

        val buttons: FloatingActionButton = findViewById(R.id.btnFloat)
        buttons.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        userRecyclerview = findViewById(R.id.user_list)
        retrieveList = ArrayList()
        adapterNew = MyAdapter(retrieveList, this)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)
        getRetrieveData(this)






    }







    private fun getRetrieveData(lists: MyAdapter.OnItemClickListener) {

            dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    retrieveList.clear()
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Retrieve::class.java)
                        retrieveList.add(user!!)
                    }
                    userRecyclerview.adapter = adapterNew
                  //  adapterNew.notifyDataSetChanged()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


    override fun onItemClick(postion: Int, value: View?) {
        val intent = Intent(this@UserActivity, Updating::class.java)
        intent.putExtra("User", retrieveList[postion])
        startActivity(intent)
    }

}