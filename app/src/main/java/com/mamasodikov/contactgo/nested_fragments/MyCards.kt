package com.mamasodikov.contactgo.nested_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamasodikov.contactgo.adapters.MyCardsAdapter
import com.mamasodikov.contactgo.R
import com.mamasodikov.contactgo.databinding.FragmentMyCardsBinding
import com.mamasodikov.contactgo.models.Contact
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.Form
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener


class MyCards:Fragment(R.layout.fragment_my_cards) {


    lateinit var binding: FragmentMyCardsBinding
    protected lateinit var ctx: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyCardsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*****Getting Current USer***/

        val pref: SharedPreferences = requireActivity().getSharedPreferences("MyPref",
            AppCompatActivity.MODE_PRIVATE)
        val currentUser: String? = pref.getString("current_user","user1")

        /*****Getting Current USer***/

        binding.addCard.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(activity, Form::class.java)
                startActivity(intent)
            }
        })



        val database: FirebaseDatabase = Firebase.database
        val myReference: DatabaseReference = database.getReference("users").child(currentUser!!).child("cards")

        val cards: MutableList <Contact> = ArrayList <Contact>()
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cards.clear()
                for (postSnapshot in snapshot.children) {
                    val card: Contact? = postSnapshot.getValue(Contact::class.java)
                    if (card != null) {
                        cards.add(card)
                    }

                }

                binding.MyCardsRecView.adapter = MyCardsAdapter (cards,ctx)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "The read failed: ${databaseError.getMessage()}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.MyCardsRecView.layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

    }
/**********most valuable part*********/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context

    }
}