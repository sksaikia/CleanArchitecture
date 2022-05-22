package com.example.githubapp.common.network

import com.example.githubapp.feature_repositories.data.remote.dto.RepoDTO
import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("/users/{user}")
    suspend fun getUserDetails(
        @Path("user") user: String
    ) : UserDTO

    @GET("/users/{user}/repos")
    suspend fun getRepoForUser(
        @Path("user") user: String
    ) : List<RepoDTO>


    companion object {
        const val BASE_URL = "https://api.github.com"
    }

}