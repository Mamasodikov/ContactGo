package com.mamasodikov.contactgo.APIs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    fun APIClient(): API {
        var retrofit: Retrofit? = null
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://data.egov.uz/apiPartner/Partner/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(API::class.java)
    }
}