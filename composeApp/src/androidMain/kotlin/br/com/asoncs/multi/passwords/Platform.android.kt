package br.com.asoncs.multi.passwords

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual val isDebug: Boolean = BuildConfig.DEBUG

actual fun getPlatform(): Platform = AndroidPlatform()
