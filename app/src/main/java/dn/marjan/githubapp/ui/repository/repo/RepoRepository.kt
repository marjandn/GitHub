package dn.marjan.githubapp.ui.repository.repo

import dn.marjan.githubapp.entity.Repository

interface RepoRepository {

    fun getUsername():String

    suspend fun getRepositories(username: String): List<Repository>
}