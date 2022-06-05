package com.example.githubapp.feature_search.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GithubUserEntity::class],
    version = 1
)
abstract class GithubUserDatabase : RoomDatabase(){

    abstract val dao : GithubUserDao

}