package com.mamasodikov.contactgo.nested_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamasodikov.contactgo.APIs.APIService
import com.mamasodikov.contactgo.adapters.GovAdapter
import com.mamasodikov.contactgo.databinding.FragmentGovermentBinding
import com.mamasodikov.contactgo.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Goverment : Fragment() {

    lateinit var binding: FragmentGovermentBinding
    protected lateinit var ctx: Context
    var id:String="6102730a2a2e256d868e832e"
    var token:String="61aa088e1b24bb9b6788c89c"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View { binding = FragmentGovermentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.GovRecView.layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        var cardBackground:String = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardEdu.cardBackgroundColor.defaultColor)
        var id= "6102730a2a2e256d868e832e"
        var token= "61aafbe51b24bb9b6788c8a8"

        loadGovs(id,token,cardBackground)

        binding.cardEdu.setOnClickListener(object: View.OnClickListener{
           override fun onClick(p0: View?) {

                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardEdu.cardBackgroundColor.defaultColor)
                id= "6102730a2a2e256d868e832e"
                token= "61aafbe51b24bb9b6788c8a8"


               loadGovs(id,token,cardBackground)

           }
       })

        binding.cardMed.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardMed.cardBackgroundColor.defaultColor)
                id= "6108c3e82a2e256d868e87a9"
                token= "61aafbe51b24bb9b6788c8a8"

                loadGovs(id,token,cardBackground)


            }
        })


        binding.cardEcon.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardEcon.cardBackgroundColor.defaultColor)
                id= "6107ce312a2e256d868e86be"
                token= "61aafbe51b24bb9b6788c8a8"

                loadGovs(id,token,cardBackground)

            }
        })


        binding.cardIT.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {


                cardBackground = java.lang.String.format("#%06X", 0xFFFFFF and binding.cardIT.cardBackgroundColor.defaultColor)
                id= "6107d1dd2a2e256d868e86e4"
                token= "61aafbe51b24bb9b6788c8a8"

                loadGovs(id,token,cardBackground)

            }
        })

        return view
    }

    private fun loadGovs(id:String, token:String, color:String) {

            APIService.APIClient().getData(id, token).enqueue(object: Callback<BaseResult<BaseData<List<BaseTable>>>> {
                override fun onResponse(
                    call: Call<BaseResult<BaseData<List<BaseTable>>>>,
                    response: Response<BaseResult<BaseData<List<BaseTable>>>>) {

                    val cardList: BaseResult<BaseData<List<BaseTable>>>
                    cardList = (response.body()) as BaseResult<BaseData<List<BaseTable>>>

                    binding.GovRecView.adapter = GovAdapter (cardList.result.data,ctx,color)

                }

                override fun onFailure(call: Call<BaseResult<BaseData<List<BaseTable>>>>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx=context
    }


}