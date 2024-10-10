package br.com.asoncs.multi.passwords.external

@JsModule("firebase/app")
external object FirebaseApp : JsAny {
    fun initializeApp(config: JsAny): JsAny
}

@JsModule("firebase/analytics")
external object FirebaseAnalytics : JsAny {
    fun getAnalytics(app: JsAny): JsAny
}

external object console : JsAny {
    fun log(any: JsAny)
}

val firebaseConfig: JsAny = js(
    """
(
    {
        apiKey: "AIzaSyC0zrF4Qs5NNbseKoICa1IYOiUtMMRJfCg",
        authDomain: "passwords-ason.firebaseapp.com",
        projectId: "passwords-ason",
        storageBucket: "passwords-ason.appspot.com",
        messagingSenderId: "821675050828",
        appId: "1:821675050828:web:bbac37cd50814b447f71e9",
        measurementId: "G-H7KHPXCE10"
    }
)
    """
)
