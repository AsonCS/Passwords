package br.com.asoncs.multi.passwords.auth

sealed class AuthException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception() {
    data object InvalidPasswordException : AuthException("InvalidPasswordException")
    data object InvalidUserException : AuthException("InvalidUserException")
    data object InvalidUserNameException : AuthException("InvalidUserNameException")

    class FirebaseAuthWeakPasswordException(
        cause: Throwable
    ) : AuthException(
        "FirebaseAuthWeakPasswordException",
        cause
    )

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

    class UnknownException(
        cause: Throwable? = null
    ) : AuthException(
        "UnknownException",
        cause
    )
}
