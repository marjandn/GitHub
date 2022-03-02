package dn.marjan.githubapp.entity

import com.google.gson.annotations.SerializedName

class Repository {
    var name: String? = null
    var description: String? = null
    var language: String? = null

    @SerializedName("updated_at")
    var lastUpdate: String? = null

    @SerializedName("stargazers_count")
    var starsCount: String? = null

    @SerializedName("fork")
    var isFork = false

    @SerializedName("forks_count")
    var forkCounts: String? = null

    @SerializedName("watchers_count")
    var watchersCount: String? = null

    var topics: List<String>? = null
}