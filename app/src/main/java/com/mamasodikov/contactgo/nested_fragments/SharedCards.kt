package com.mamasodikov.contactgo.nested_fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mamasodikov.contactgo.R
import com.mamasodikov.contactgo.databinding.FragmentSharedCardsBinding
import com.mamasodikov.contactgo.models.Contact
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import com.mamasodikov.contactgo.qr_activities.Scanner
import com.mamasodikov.contactgo.adapters.SharedCardsAdapter


class SharedCards : Fragment(R.layout.fragment_shared_cards) {


    lateinit var binding: FragmentSharedCardsBinding
    protected lateinit var ctx: Context
    val FilteredCards: MutableList<Contact> = ArrayList<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSharedCardsBinding.inflate(inflater, container, false)
        val view = binding.root

        /*****Getting Current USer***/

        val pref: SharedPreferences = requireActivity().getSharedPreferences(
            "MyPref",
            AppCompatActivity.MODE_PRIVATE
        )
        val currentUser: String? = pref.getString("current_user", "user1")
        val sharedUser: String
        val sharedCardsId: MutableList<String> = ArrayList()

        if (currentUser == "user1") {
            sharedUser = "user2"
        } else {
            sharedUser = "user1"
        }

        /**************************************************************************************/

        //  var maxSize:Long=0
        val database: FirebaseDatabase = Firebase.database
        val myReference: DatabaseReference = database.getReference("users")

        binding.SharedCardsRecView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        /********************Event Listener For Shared Cards & count**************************/


        myReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())

                    sharedCardsId.clear()
                FilteredCards.clear()

                for (postSnapshot in snapshot.child(currentUser!!).child("shared").children) {
                    val cardIDs: String? = postSnapshot.getValue(String::class.java)
                    if (cardIDs != null) {
                        sharedCardsId.add(cardIDs)
                        //Toast.makeText(context, sharedCardsId.size.toString(),Toast.LENGTH_SHORT).show()
                    }
                }

                for (postSnapshot in snapshot.child(sharedUser).child("cards").children) {
                    val card: Contact? = postSnapshot.getValue(Contact::class.java)
                    if (card != null) {
                        for (id in sharedCardsId) {
                            if (card.contact_id == id)
                                FilteredCards.add(card)
                        }
                    }
                }

                binding.SharedCardsRecView.adapter = SharedCardsAdapter(FilteredCards, ctx)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    context,
                    "The read failed: ${databaseError.getMessage()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


        /********************************************************************************************/

        /*************Adding Data**************/
        binding.scan.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                val intent = Intent(context, Scanner::class.java)
                startActivity(intent)
            }
        })

        return view
    }

    /**********************************************************************************************/

    @SuppressLint("NotifyDataSetChanged")
    fun Notify() {
        binding.SharedCardsRecView.adapter = SharedCardsAdapter(FilteredCards, requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.RecViewFirst.adapter = MenuAdapter(menus)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }
}