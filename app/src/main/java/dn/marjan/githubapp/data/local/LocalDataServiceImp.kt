package dn.marjan.githubapp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dn.marjan.githubapp.data.local.LocalDataServiceImp.PreferenceHelper.get
import dn.marjan.githubapp.data.local.LocalDataServiceImp.PreferenceHelper.set
import dn.marjan.githubapp.entity.UserInfo
import javax.inject.Inject

class LocalDataServiceImp @Inject constructor(val context: Context) : LocalDataService {

    private var session: SharedPreferences

    init {
        session = PreferenceHelper.defaultPrefs(context)
    }


    object PreferenceHelper {

        fun defaultPrefs(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        fun customPrefs(context: Context, name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

        inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
            val editor = this.edit()
            operation(editor)
            editor.apply()
        }

        /**
         * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
         *
         * .AccountSession.PreferenceHelper.set
         * and
         * .AccountSession.PreferenceHelper.get
         * in which activity you want to use it
         */
        operator fun SharedPreferences.set(key: String, value: Any) {
            when (value) {
                is String -> edit { it.putString(key, value) }
                is Int -> edit { it.putInt(key, value) }
                is Boolean -> edit { it.putBoolean(key, value) }
                is Float -> edit { it.putFloat(key, value) }
                is Long -> edit { it.putLong(key, value) }
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }

        /**
         * finds value on given key.
         * [T] is the type of value
         * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
         */
        inline operator fun <reified T : Any> SharedPreferences.get(
            key: String,
            defaultValue: T
        ): T {
            return when (T::class) {
                String::class -> getString(key, defaultValue as String) as T
                Int::class -> getInt(key, defaultValue as Int) as T
                Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
                Float::class -> getFloat(key, defaultValue as Float) as T
                Long::class -> getLong(key, defaultValue as Long) as T
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    private val prefUsername = "pref_username"
    private val prefUserImage = "pref_user_image"
    private val prefUserGitHubUrl = "pref_user_github_url"
    private val prefUserFullName = "pref_user_fullname"
    private val prefUserBlog = "pref_user_blog"
    private val prefUserLocation = "pref_user_location"
    private val prefUserEmail = "pref_user_email"
    private val prefUserFollowers = "pref_user_followers"
    private val prefUserFollowing = "pref_user_following"
    private val prefUserReposCount = "pref_user_repos_count"

    override fun saveUserData(user: UserInfo) {
        session[prefUsername] = user.login.toString()
        session[prefUserImage] = user.avatarUrl.toString()
        session[prefUserGitHubUrl] = user.url.toString()
        session[prefUserFullName] = user.name.toString()
        session[prefUserBlog] = user.blog.toString()
        session[prefUserLocation] = user.location.toString()
        session[prefUserEmail] = user.email.toString()
        session[prefUserFollowers] = user.followers.toString()
        session[prefUserFollowing] = user.following.toString()
        session[prefUserReposCount] = user.publicRepos.toString()
    }

    override fun getFullName(): String = session[prefUserFullName, ""]

    override fun getUsername(): String = session[prefUsername, ""]

    override fun getFollowers(): String = session[prefUserFollowers, ""]

    override fun getFollowing(): String = session[prefUserFollowing, ""]

    override fun getBlog(): String = session[prefUserBlog, ""]

    override fun getLocation(): String = session[prefUserLocation, ""]

    override fun getReposCount(): String = session[prefUserReposCount, ""]

    override fun getUserImage(): String = session[prefUserImage, ""]

    override fun isUserLogin(): Boolean = session.contains(prefUsername)

}