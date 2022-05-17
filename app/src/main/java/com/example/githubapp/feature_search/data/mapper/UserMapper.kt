package com.example.githubapp.feature_search.data.mapper

import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import com.example.githubapp.feature_search.domain.model.User

class UserMapper {

    fun mapUserToDomain(userDTO: UserDTO): User {
        return User(
            avatar_url = userDTO.avatar_url,
            bio = userDTO.bio,
            company = userDTO.company,
            created_at = userDTO.created_at,
            followers = userDTO.followers,
            following = userDTO.following,
            location = userDTO.location,
            login = userDTO.login,
            name = userDTO.name,
            public_repos = userDTO.public_repos
        )
    }

}