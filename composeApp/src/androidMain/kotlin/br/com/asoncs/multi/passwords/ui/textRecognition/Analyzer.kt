package br.com.asoncs.multi.passwords.ui.textRecognition

import br.com.asoncs.multi.passwords.camera.CameraAnalyzer
import br.com.asoncs.multi.passwords.camera.TAG_CAMERA
import br.com.asoncs.multi.passwords.extension.error
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

internal class Analyzer(
    private val useJapanese: () -> Boolean,
    private val onResult: (String) -> Unit
) : CameraAnalyzer() {

    override fun analyze(
        image: InputImage
    ) = TextRecognition.getClient(
        if (useJapanese())
            JapaneseTextRecognizerOptions.Builder()
                .build()
        else
            TextRecognizerOptions.DEFAULT_OPTIONS
    ).process(image)
        .addOnSuccessListener { visionText ->
            if (visionText.text.isBlank()) return@addOnSuccessListener

            onResult(
                visionText.text
            )
        }
        .addOnFailureListener {
            TAG_CAMERA.error("TextRecognition.Analyzer", it)
        }
}
