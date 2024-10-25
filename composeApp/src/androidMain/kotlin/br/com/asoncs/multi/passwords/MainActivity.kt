package br.com.asoncs.multi.passwords

import android.content.Context
import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import br.com.asoncs.multi.passwords.auth.*
import br.com.asoncs.multi.passwords.data.firebase.AuthRepository
import br.com.asoncs.multi.passwords.extension.error
import br.com.asoncs.multi.passwords.extension.log
import br.com.asoncs.multi.passwords.ui.app.App
import br.com.asoncs.multi.passwords.ui.app.TAG_APP
import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

class MainActivity : ComponentActivity(),
    KoinComponent,
    AuthAndroidV1,
    AuthAndroidV2 {

    override val activity = this
    override var emit: (AuthState) -> Unit = {}
    override val repository by inject<AuthRepository>()
    override val signInLauncher = signInLauncher()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            App(
                auth = this, // */ AuthMock,
                platformModule = module {
                    factory<Context> { this@MainActivity }
                }
            )
        }

        lifecycleScope.launch {
            testGemini()
        }
    }

    override suspend fun loginWithGoogle() {
        // TODO Check NoClassDefFoundError super<AuthAndroidV2>.loginWithGoogle()
        super<AuthAndroidV1>.loginWithGoogle()
    }

    @Suppress("UNREACHABLE_CODE")
    private suspend fun testGemini() {
        runCatching {
            TODO("Test Gemini")

            // Initialize the Vertex AI service and the generative model
            // Specify a model that supports your use case
            // Gemini 1.5 models are versatile and can be used with all API capabilities
            val generativeModel = Firebase
                .vertexAI
                .generativeModel("gemini-1.5-flash")

            // Provide a prompt that contains text
            val prompt = "Write a story about a magic backpack."

            // To generate text output, call generateContent with the text input
            val response = generativeModel
                .generateContent(prompt)

            TAG_APP.log(
                response.text
                    ?: "empty response"
            )
        }.onFailure {
            TAG_APP.error(
                it.message ?: "error"
            )
        }
    }

}
