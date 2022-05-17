package com.example.githubapp.common.network

import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("/users/{user}")
    suspend fun getUserDetails(
        @Path("user") user: String
    ) : UserDTO


    companion object {
        const val BASE_URL = "https://api.github.com"
    }

}