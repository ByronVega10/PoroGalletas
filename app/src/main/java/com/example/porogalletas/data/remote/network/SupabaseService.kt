package com.example.porogalletas.data.remote.network

import com.example.porogalletas.data.remote.api.PlatillosApi

object SupabaseService {
    val platillosApi: PlatillosApi =
        RetrofitClient.retrofit.create(PlatillosApi::class.java)
}