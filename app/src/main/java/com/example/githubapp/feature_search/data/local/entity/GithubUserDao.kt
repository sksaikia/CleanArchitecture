package com.example.githubapp.feature_search.data.local.entity

import androidx.room.*

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(info : GithubUserEntity)

    @Query("DELETE FROM githubuserentity WHERE userName is :user")
    suspend fun deleteUser(user : String)

    @Query("SELECT * FROM githubuserentity WHERE userName is :user")
    suspend fun getUser(user: String) : GithubUserEntity
}