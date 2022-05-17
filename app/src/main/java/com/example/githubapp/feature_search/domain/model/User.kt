package com.example.githubapp.feature_search.domain.model

data class User(
    val avatar_url: String?,
    val bio: String?,
    val company: String?,
    val created_at: String,
    val followers: Int,
    val following: Int,
    val location: String?,
    val login: String,
    val name: String?,
    val public_repos: Int
)