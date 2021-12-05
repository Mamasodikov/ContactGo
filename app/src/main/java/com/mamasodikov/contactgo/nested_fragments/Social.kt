package com.mamasodikov.contactgo.nested_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamasodikov.contactgo.adapters.SocAdapter
import com.mamasodikov.contactgo.databinding.FragmentSocialBinding
import com.mamasodikov.contactgo.models.Contact




class Social : Fragment() {

    lateinit var binding: FragmentSocialBinding
    protected lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View { binding = FragmentSocialBinding.inflate(inflater, container, false)
        val view = binding.root

        val cards: MutableList <Contact> = ArrayList ()
        var cardBackground:String = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardBusiness.cardBackgroundColor.defaultColor)

        binding.SocRecView.layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardBusiness.cardBackgroundColor.defaultColor)

        /****************Adding HARD CODE FOR TEST**************/

        cards.clear()
        cards.add(Contact("1","Mirakmal", "Payziyev","Tadbirkor", "+998901234567","t.me/akmalpayziyev",
            "facebook.com/payziyev", "instagram.com/menakmal"))


        cards.add(Contact("2","Sobir", "Akilxanov","Investor", "+998900000000","t.me/sakilhanov",
            "facebook.com/sobirakilhkhanov", "instagram.com/sobby"))


        binding.SocRecView.adapter =  SocAdapter (cards,ctx,cardBackground)


        binding.cardBusiness.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardBusiness.cardBackgroundColor.defaultColor)

                /****************Adding HARD CODE FOR TEST**************/

                cards.clear()
                cards.add(Contact("1","Mirakmal", "Payziyev","Tadbirkor", "+998901234567","t.me/akmalpayziyev",
            "facebook.com/payziyev", "instagram.com/menakmal"))


                cards.add(Contact("2","Sobir", "Akilxanov","Investor", "+998900000000","t.me/sakilhanov",
            "facebook.com/sobirakilhkhanov", "instagram.com/sobby"))


                binding.SocRecView.adapter =  SocAdapter (cards,ctx,cardBackground)
            }
        })

        binding.cardSport.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                /****************Adding HARD CODE FOR TEST**************/

                cards.clear()
                cards.add(Contact("1","Aziz", "Komilov","Fitness trener", "+998950000000","t.me/azizatlet",
                    "facebook.com/kamilovaziz", "instagram.com/azikbro"))


                cards.add(Contact("2","Artyom", "Mixailov","Boks treneri", "+99899123121","t.me/boxinme",
                    "facebook.com/artyem", "instagram.com/artik"))


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardSport.cardBackgroundColor.defaultColor)
                binding.SocRecView.adapter =  SocAdapter (cards,ctx,cardBackground)

            }
        })


        binding.cardBuild.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                /****************Adding HARD CODE FOR TEST**************/

                cards.clear()
                cards.add(Contact("1","Toshmurad", "Nazarov","Qurilish sohasida tadbirkor", "+998950000000","t.me/ermatkarimov",
                    "facebook.com/toshmurad", "instagram.com/toshmuradbuildings"))


                cards.add(Contact("2","Rishod", "Qosimov","Arxitektor", "+998950000101","t.me/archuz",
                    "facebook.com/archics", "instagram.com/proektor"))


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardBuild.cardBackgroundColor.defaultColor)
                binding.SocRecView.adapter =  SocAdapter (cards,ctx,cardBackground)
            }
        })


        binding.cardRealtor.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                /****************Adding HARD CODE FOR TEST**************/

                cards.clear()
                cards.add(Contact("1","Ermat", "Sobirov","Realtor", "+998950000000","t.me/ermatkarimov",
                    "facebook.com/realtorsobir", "instagram.com/sobirbro"))


                cards.add(Contact("2","Farrux", "Sotarov","Realtor", "+998950000000","t.me/yangiuylar",
                    "facebook.com/farruxrealtor", "instagram.com/yangiuylar"))


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardRealtor.cardBackgroundColor.defaultColor)
                binding.SocRecView.adapter =  SocAdapter (cards,ctx,cardBackground)
            }
        })


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx=context
    }

}