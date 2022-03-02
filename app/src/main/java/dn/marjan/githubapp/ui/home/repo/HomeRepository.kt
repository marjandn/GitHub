package dn.marjan.githubapp.ui.home.repo

import dn.marjan.githubapp.entity.ReceivedEvents

interface HomeRepository {
    suspend fun getReceivedEvents(username:String) :  List<ReceivedEvents>

    fun getUsername():String
}