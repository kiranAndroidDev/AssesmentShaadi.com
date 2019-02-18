package com.example.demoshaadi.api.model

import com.google.gson.annotations.SerializedName

public class Response{
    @SerializedName("results")
    private val results: List<Results>? = null

    fun getResults(): List<Results>? {
        return results
    }
}