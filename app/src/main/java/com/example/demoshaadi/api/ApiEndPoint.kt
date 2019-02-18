package com.example.demoshaadi.api

import com.example.demoshaadi.api.model.Response

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiEndPoint {

    @GET(ApiConstants.USER_URL)
    fun fetchList(): Observable<Response>
}
