package br.com.asoncs.multi.passwords

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /*
        val optionsBuilder = FirebaseVisionCloudImageLabelerOptions.Builder()
        if (!BuildConfig.DEBUG) {
            // Requires physical, non-rooted device:
            optionsBuilder.enforceCertFingerprintMatch()
        }

        // Set other options. For example:
        optionsBuilder.setConfidenceThreshold(0.8f)
        // ...

        // And lastly:
        val options = optionsBuilder.build()
        FirebaseVision.getInstance().getCloudImageLabeler(options).processImage(myImage)
        */
    }

}
