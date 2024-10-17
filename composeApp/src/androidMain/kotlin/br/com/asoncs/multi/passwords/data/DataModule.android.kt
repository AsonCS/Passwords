@file:Suppress("UNCHECKED_CAST")

package br.com.asoncs.multi.passwords.data

import android.content.Context
import androidx.datastore.preferences.core.*
import br.com.asoncs.multi.passwords.data.PlatformDataModule.DataStore
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KClass

internal actual val platform = object : PlatformDataModule(
    engine = OkHttp
) {
    override val dataStore by lazy {
        getDataStore(dataStoreFileName)
    }
}

private fun getDataStore(
    fileName: String
) = object : DataStore, KoinComponent {

    private val context by inject<Context>()

    private val _dataStore = PreferenceDataStoreFactory
        .createWithPath(
            produceFile = {
                context
                    .filesDir
                    .resolve(fileName)
                    .absolutePath
                    .toPath()
            }
        )

    override fun <T : Any> createPreference(
        cls: KClass<T>,
        key: String
    ) = object : DataStore.Preference<T> {

        private val preferenceKey = when (cls) {
            Boolean::javaClass -> booleanPreferencesKey(key)
            Int::javaClass -> intPreferencesKey(key)
            else -> stringPreferencesKey(key)
        } as Preferences.Key<T>

        override suspend fun set(
            value: T?
        ) {
            _dataStore.edit { settings ->
                if (value == null) {
                    settings.remove(preferenceKey)
                } else {
                    settings[preferenceKey] = value
                }
            }
        }

        override suspend fun get() = _dataStore
            .data
            .first()[preferenceKey]
    }

}
