package dn.marjan.githubapp.data

sealed class Resource<T>(
    val status: Status = Status.UNKNOWN,
    val data: T? = null,
    val message: String? = null,
    val error: Error? = null
) {

}


class SuccessResource<T>(data: T) : Resource<T>(status = Status.SUCCESS, data = data)

class ErrorResource<T>(error: Error, message: String = "") :
    Resource<T>(status = Status.ERROR, error = error, message = message)

/*fun  loading(): Resource =
    Resource(status = Status.LOADING)*/


/*data class Resource<T>(
    val status: Status = Status.UNKNOWN,
    val data: T? = null,
    val message: String? = null,
    val error: Error? = null
) {

    companion object {

        fun  success(data: Any): Resource<T> =
            Resource(status = Status.SUCCESS, data = data)

        fun  error(error: Error , message: String=""): Resource<Any> =
            Resource(status = Status.ERROR , error= error, message = message )

        fun  loading(): Resource<Any> =
            Resource(status = Status.LOADING)
    }
}*/
