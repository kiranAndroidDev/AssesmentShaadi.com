package com.example.demoshaadi.api


import com.example.demoshaadi.api.model.Response

import javax.inject.Inject

import io.reactivex.Observable


class ApiClient @Inject
constructor(private val api: ApiEndPoint) {

    fun fetchList(): Observable<Response> {
        return api.fetchList()
    }

}
