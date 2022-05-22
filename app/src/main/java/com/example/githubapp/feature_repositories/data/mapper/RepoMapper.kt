package com.example.githubapp.feature_repositories.data.mapper

import com.example.githubapp.feature_repositories.data.remote.dto.OwnerDTO
import com.example.githubapp.feature_repositories.data.remote.dto.RepoDTO
import com.example.githubapp.feature_repositories.domain.model.Owner
import com.example.githubapp.feature_repositories.domain.model.Repo

class RepoMapper {

    fun mapOwnerToDomain(ownerDTO : OwnerDTO) : Owner {
        return Owner(
            avatar_url = ownerDTO.avatar_url,
            login = ownerDTO.login
        )
    }

    fun mapRepoListToDomain(repoDTOs : List<RepoDTO>) : List<Repo> {
        val repos = mutableListOf<Repo>()
        repoDTOs.forEach {
            repos.add(mapRepoToDomain(it))
        }

        return repos

    }

    fun mapRepoToDomain(repoDTO : RepoDTO) : Repo {
        return Repo(
           name = repoDTO.name,
           language = repoDTO.language,
           owner = mapOwnerToDomain(repoDTO.owner)
        )
    }

}