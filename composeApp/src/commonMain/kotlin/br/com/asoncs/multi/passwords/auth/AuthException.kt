package br.com.asoncs.multi.passwords.auth

sealed class AuthException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception() {
    data object InvalidUserException : AuthException("InvalidUserException")

    class GetCredentialCancellationException(
        cause: Throwable
    ) : AuthException(
        "GetCredentialCancellationException",
        cause
    )

    class GetCredentialException(
        cause: Throwable
    ) : AuthException(
        "GetCredentialException",
        cause
    )

    class NoCredentialException(
        cause: Throwable
    ) : AuthException(
        "NoCredentialException",
        cause
    )

    data object UnknownException : AuthException("UnknownException")
}
