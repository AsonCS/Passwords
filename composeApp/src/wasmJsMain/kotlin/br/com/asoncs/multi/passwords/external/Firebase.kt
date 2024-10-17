@file:Suppress("FunctionName")

package br.com.asoncs.multi.passwords.external

import br.com.asoncs.multi.passwords.generated.BuildConfig
import kotlin.js.Promise

@JsModule("firebase/app")
external object FirebaseApp : JsAny {
    fun initializeApp(
        config: JsAny
    ): JsAny
}

//  @JsModule("firebase/analytics")
//  external object FirebaseAnalytics : JsAny {
//      fun getAnalytics(
//          app: JsAny
//      ): JsAny
//  }

@JsModule("firebase/auth")
external object FirebaseAuth : JsAny {

    fun createUserWithEmailAndPassword(
        auth: Auth,
        email: String,
        password: String
    ): Promise<JsAny>

    fun getAuth(
        app: JsAny
    ): Auth

    fun getRedirectResult(
        auth: Auth
    ): Promise<JsAny>

    fun onAuthStateChanged(
        auth: Auth,
        callback: () -> Unit
    )

    fun signInWithPopup(
        auth: Auth,
        provider: JsAny
    ): Promise<JsAny>

    fun signInWithEmailAndPassword(
        auth: Auth,
        email: String,
        password: String
    ): Promise<JsAny>

    fun signOut(
        auth: Auth
    ): Promise<JsAny>

    class Auth : JsAny {
        val currentUser: User?
        var languageCode: String
    }

    class Credential : JsAny {
        val accessToken: String
    }

    class GoogleAuthProvider : JsAny {
        companion object {
            fun credentialFromResult(
                result: JsAny
            ): Credential
        }
    }

    class User : JsAny {
        val accessToken: String?
        val displayName: String?
        val email: String?
        val photoURL: String?
        val uid: String
    }

}

val firebaseConfig: JsAny = js(
    """
            (
                {
                    appId: "${BuildConfig.FIREBASE_APP_ID}",
                    apiKey: "${BuildConfig.FIREBASE_API_KEY}",
                    authDomain: "${BuildConfig.FIREBASE_AUTH_DOMAIN}",
                    measurementId: "${BuildConfig.FIREBASE_MEASUREMENT_ID}",
                    messagingSenderId: "${BuildConfig.FIREBASE_MESSAGING_SENDER_ID}",
                    projectId: "${BuildConfig.FIREBASE_PROJECT_ID}",
                    storageBucket: "${BuildConfig.FIREBASE_STORAGE_BUCKET}"
                }
            )
        """
)
