package com.example.githubapp.feature_search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubUserEntity(
    val userName : String,
    val userRepository : Int,
    @PrimaryKey
    val id : Int? = null
)
