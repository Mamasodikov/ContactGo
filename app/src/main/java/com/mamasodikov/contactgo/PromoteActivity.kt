package com.mamasodikov.contactgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.mamasodikov.contactgo.adapters.PromoteAdapter
import com.mamasodikov.contactgo.adapters.SocAdapter
import com.mamasodikov.contactgo.databinding.ActivityMainBinding
import com.mamasodikov.contactgo.databinding.ActivityPromoteBinding
import com.mamasodikov.contactgo.databinding.PromoCardsItemBinding
import com.mamasodikov.contactgo.models.Contact

class PromoteActivity : AppCompatActivity() {

    lateinit var b: ActivityPromoteBinding
    val cards: MutableList <Contact> = ArrayList ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPromoteBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.promoCardsRecView.layoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        cards.clear()

        /************Adding only for visualization**************/
        cards.add(
            Contact("1","Mirakmal", "Payziyev","Tadbirkor", "+998901234567","t.me/akmalpayziyev",
            "facebook.com/payziyev", "instagram.com/menakmal")
        )

        cards.add(
            Contact("2","Sobir", "Akilxanov","Investor", "+998900000000","t.me/sakilhanov",
            "facebook.com/sobirakilhkhanov", "instagram.com/sobby")
        )


        b.promoCardsRecView.adapter =  PromoteAdapter (cards)

    }

}