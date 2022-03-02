package dn.marjan.githubapp.data


sealed class Error: Throwable() {
    object NetworkErrors: Error()
    object EmptyInputError: Error()
    object EmptyResultError: Error()
    object SingleError: Error()
}