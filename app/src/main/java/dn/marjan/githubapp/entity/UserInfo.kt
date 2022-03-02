package dn.marjan.githubapp.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserInfo {
    @SerializedName("login")
    @Expose
     val login: String? = null

    @SerializedName("id")
    @Expose
     val id: Int? = null

    @SerializedName("node_id")
    @Expose
     val nodeId: String? = null

    @SerializedName("avatar_url")
    @Expose
     val avatarUrl: String? = null

    @SerializedName("gravatar_id")
    @Expose
     val gravatarId: String? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

    @SerializedName("html_url")
    @Expose
     val htmlUrl: String? = null

    @SerializedName("followers_url")
    @Expose
     val followersUrl: String? = null

    @SerializedName("following_url")
    @Expose
     val followingUrl: String? = null

    @SerializedName("gists_url")
    @Expose
     val gistsUrl: String? = null

    @SerializedName("starred_url")
    @Expose
     val starredUrl: String? = null

    @SerializedName("subscriptions_url")
    @Expose
     val subscriptionsUrl: String? = null

    @SerializedName("organizations_url")
    @Expose
     val organizationsUrl: String? = null

    @SerializedName("repos_url")
    @Expose
     val reposUrl: String? = null

    @SerializedName("events_url")
    @Expose
     val eventsUrl: String? = null

    @SerializedName("received_events_url")
    @Expose
     val receivedEventsUrl: String? = null

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("site_admin")
    @Expose
     val siteAdmin: Boolean? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("company")
    @Expose
     val company: Any? = null

    @SerializedName("blog")
    @Expose
     val blog: String? = null

    @SerializedName("location")
    @Expose
     val location: String? = null

    @SerializedName("email")
    @Expose
     val email: String? = null

    @SerializedName("hireable")
    @Expose
     val hireable: Any? = null

    @SerializedName("bio")
    @Expose
     val bio: String? = null

    @SerializedName("twitter_username")
    @Expose
     val twitterUsername: Any? = null

    @SerializedName("public_repos")
    @Expose
     val publicRepos: Int? = null

    @SerializedName("public_gists")
    @Expose
     val publicGists: Int? = null

    @SerializedName("followers")
    @Expose
     val followers: Int? = null

    @SerializedName("following")
    @Expose
     val following: Int? = null

    @SerializedName("created_at")
    @Expose
     val createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
     val updatedAt: String? = null

    @SerializedName("_gists")
    @Expose
     val Gists: Int? = null

    @SerializedName("total__repos")
    @Expose
     val totalRepos: Int? = null

    @SerializedName("owned__repos")
    @Expose
     val ownedRepos: Int? = null

    @SerializedName("disk_usage")
    @Expose
     val diskUsage: Int? = null

    @SerializedName("collaborators")
    @Expose
     val collaborators: Int? = null

    @SerializedName("two_factor_authentication")
    @Expose
     val twoFactorAuthentication: Boolean? = null

    @SerializedName("plan")
    @Expose
     val plan: Plan? = null

    class Plan{

        @SerializedName("name")
        @Expose
         val name: String? = null

        @SerializedName("space")
        @Expose
         val space: Int? = null

        @SerializedName("collaborators")
        @Expose
         val collaborators: Int? = null

        @SerializedName("_repos")
        @Expose
         val Repos: Int? = null
    }
}
