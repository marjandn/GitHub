package dn.marjan.githubapp.entity

class ReceivedEvents {
    var id = ""
    var type = ""
    var actor: Actor? = null
    var repo: Repository? = null
    var payload: Payload? = null


    class Payload {
        var action = ""
    }

    class Repository {
        var name = ""
        var url = ""
    }

    class Actor {
        var login = ""
        var avatar_url = ""
        var url = ""
    }
}