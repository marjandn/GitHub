package dn.marjan.githubapp.utils

import android.util.Log
import androidx.compose.material.Snackbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import dn.marjan.githubapp.R
import dn.marjan.githubapp.entity.ReceivedEvents

fun log(msg: String){
    Log.d("MARJANTAG", msg);
}


fun getAction(item: ReceivedEvents): String {
    if (item.payload!!.action.isNotEmpty()) {
        return item.payload!!.action
    } else {
        when (item.type) {
            "ForkEvent" -> return "forked"
            "CreateEvent" -> return "created a repository"
            "PublicEvent" -> return "made"
        }
    }
    return ""
}

fun getLanguageColor(lang: String): Int {
    return when(lang){
        "Java" -> R.color.JavaColor
        "Kotlin" -> R.color.KotlinColor
        "Dart" -> R.color.DartColor
        else -> R.color.HtmlColor
    }
}