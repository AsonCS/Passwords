package br.com.asoncs.multi.passwords.scan

import com.google.mlkit.vision.barcode.common.Barcode

sealed class ScanResult {

    data class Error(
        val message: String
    ) : ScanResult()

    data class Success(
        val format: Int?,
        val rawValue: String?,
        val type: Int?,
        val url: String?,
        val wifi: String?
    ) : ScanResult() {
        constructor(
            barcode: Barcode
        ) : this(
            format = barcode.format,
            rawValue = barcode.rawValue,
            type = barcode.valueType,
            url = barcode.url
                ?.let {
                    "title: ${it.title}, url: ${it.url}"
                },
            wifi = barcode.wifi
                ?.let {
                    "password: ${it.password}, ssid: ${it.ssid}, type: ${it.encryptionType}"
                }
        )

        override fun toString(): String {
            val format = if (format != null)
                "format: $format\n"
            else
                ""
            val type = if (type != null)
                "type: $type\n"
            else
                ""
            val url = if (url != null)
                "url: $url\n"
            else
                ""
            val wifi = if (wifi != null)
                "wifi: $wifi\n"
            else
                ""

            return "\n--- Result ---\n$format$type$url${wifi}rawValue: $rawValue\n"
        }
    }

}
