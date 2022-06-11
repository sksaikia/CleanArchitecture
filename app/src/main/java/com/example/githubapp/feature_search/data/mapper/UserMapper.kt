package com.example.githubapp.feature_search.data.mapper

import com.example.githubapp.feature_search.data.local.entity.GithubUserEntity
import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import com.example.githubapp.feature_search.domain.model.User

class UserMapper {

    fun mapUserDTOToDomain(userDTO: UserDTO): User {
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

    fun mapUserEntityToDomain(userEntity: GithubUserEntity) : User {
        return User(
            public_repos = userEntity.userRepository,
            login = userEntity.userName,
            avatar_url = "",
            bio = "",
            company = "",
            created_at = "",
            followers = 0,
            following = 0,
            location = "",
            name = "",
        )
    }

    fun mapUserToUserEntity(user : User) : GithubUserEntity {
        return GithubUserEntity(
            userName = user.name!!,
            userRepository = user.public_repos
        )
    }

    fun mapUserDTOToEntity(dto : UserDTO) : GithubUserEntity {
        return GithubUserEntity(
            userName = dto.login,
            userRepository = dto.public_repos
        )
    }

}